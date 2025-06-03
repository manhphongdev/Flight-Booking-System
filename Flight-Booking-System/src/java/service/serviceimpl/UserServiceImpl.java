package service.serviceimpl;

import config.email.EmailConfig;
import dao.IEmailAuthenticationDAO;
import dao.IUserDAO;
import dao.daoimpl.EmailAuthenticationDAOImpl;
import dao.daoimpl.UserDAOImpl;
import enums.UserStatus;
import exception.EntityExistExeption;
import exception.EntityNotFoundException;
import exception.UserInActiveException;
import java.time.LocalDateTime;
import java.util.Optional;
import model.UserEntity;
import service.IUserService;
import utils.ErrorMSG;
import utils.PasswordEncryption;
import utils.RandomNumber;

/**
 *
 * @author manhphong
 */
public class UserServiceImpl implements IUserService {

    private final IUserDAO uDao;
    private final IEmailAuthenticationDAO verifyDAO;

    public UserServiceImpl() {
        this.uDao = new UserDAOImpl();
        this.verifyDAO = new EmailAuthenticationDAOImpl();
    }

    @Override
    public boolean register(UserEntity user) {
        if (isUserExist(user)) {
            throw new EntityExistExeption("Email is exist!" + user.getEmail());
        } else {
            user.setPassword(PasswordEncryption.doSha256(user.getPassword()));
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            user.setLastLoginedAt(LocalDateTime.now());
            user.setStatus(UserStatus.PENDING);

            Long id = uDao.insert(user);
            user.setUserId(id);

            String code = RandomNumber.getRandomNumber();
            if (verifyDAO.insertVerify(id, code, LocalDateTime.now(), UserStatus.PENDING.toString()) != null) {
                String fullName = user.getLastName() + " " + user.getFirstName();
                //send email verify
                EmailConfig.sendEmail(getContentAuthentication(fullName, id, code), user.getEmail());
            }
            uDao.addUserHasRole(user);
            return true;
        }
    }

    public String getContentAuthentication(String fullName, Long userID, String code) {
        String url = "http://localhost:8080/flights/authentication?action=verify&id=" + userID + "&token=" + code;
        String content = "<p>Xin ch&agrave;o <strong>" + fullName + "</strong>,</p>\n"
                + "<p>Vui lòng xác thực tài khoản của bạn bằng cách click vào đường dẫn sau:</p>\n"
                + "<p><a href=\"" + url + "\">" + url + "</a></p>\n"
                + "<p>Đây là email tự động, vui lòng không phản hồi email này!</p>\n"
                + "<p>Trân trọng cảm ơn!</p>";
        return content;
    }

    public boolean isUserExist(UserEntity user) {
        Optional<UserEntity> userCheckExist = uDao.findByEmail(user.getEmail());
        return userCheckExist.isPresent();
    }

    @Override
    public UserEntity login(String email, String password) {
        String passEncrypted = PasswordEncryption.doSha256(password);
        UserEntity foundUser = uDao.findByEmailAndPassword(email, passEncrypted)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMSG.ENTITY_NOT_FOUND));

        if (foundUser.getStatus() != UserStatus.ACTIVE) {
            throw new UserInActiveException(ErrorMSG.ACCOUNT_IN_ACTICE);
        }
        return foundUser;
    }

    @Override
    public String getUserRole(UserEntity user) {
        return uDao.getUserRole(user);
    }

    public UserEntity getUserById(Long id) {
        return uDao.findByID(id).orElse(null);
    }
}
