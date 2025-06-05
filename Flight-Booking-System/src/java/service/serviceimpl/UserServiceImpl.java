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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import model.User;
import service.interfaces.IUserService;
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
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class.getName());

    public UserServiceImpl() {
        this.uDao = new UserDAOImpl();
        this.verifyDAO = new EmailAuthenticationDAOImpl();
    }

    @Override
    public boolean register(User user) {
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
            if (verifyDAO.insertVerify(id, code, LocalDateTime.now().plusDays(1), UserStatus.PENDING.toString()) != null) {
                String fullName = user.getLastName() + " " + user.getFirstName();
                //send email verify
                EmailConfig.sendEmail(getContentAuthentication(fullName, id, code), user.getEmail());
            }
            uDao.addUserHasRole(user);
            return true;
        }
    }

    @Override
    public boolean addUser(User user) {
        if (isUserExist(user)) {
            throw new EntityExistExeption("Email is exist!" + user.getEmail());
        } else {
            user.setPassword(PasswordEncryption.doSha256(user.getPassword()));
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            user.setLastLoginedAt(LocalDateTime.now());
            user.setStatus(UserStatus.ACTIVE);

            Long id = uDao.insert(user);
            user.setUserId(id);
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

    public boolean isUserExist(User user) {
        Optional<User> userCheckExist = uDao.findByEmail(user.getEmail());
        return userCheckExist.isPresent();
    }

    @Override
    public User login(String email, String password) {
        String passEncrypted = PasswordEncryption.doSha256(password);
        User foundUser = uDao.findByEmailAndPassword(email, passEncrypted)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMSG.ENTITY_NOT_FOUND));

        if (foundUser.getStatus() != UserStatus.ACTIVE) {
            throw new UserInActiveException(ErrorMSG.ACCOUNT_IN_ACTICE);
        }
        return foundUser;
    }

    @Override
    public String getUserRole(User user) {
        return uDao.getUserRole(user);
    }

    public User getUserById(Long id) {
        return uDao.findByID(id).orElse(null);
    }

    @Override
    public void updateUser(User entity, Long id) {
        boolean isUpdated = uDao.updateByID(id, entity);
        if (isUpdated == false) {
            throw new EntityNotFoundException(ErrorMSG.ENTITY_NOT_FOUND);
        }
    }

    @Override
    public List<User> getAll() {
        return uDao.findAll();
    }


    @Override
    public void deleteUser(Long id) {
        uDao.deleteByID(id);
    }

    @Override
    public void adminUpdateUser(User newUser) {
        if (getUserById(newUser.getUserId()) == null) {
            LOG.warning("User does not exists!");
            return;
        }
        User user = getUserById(newUser.getUserId());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setUserRole(newUser.getUserRole());
        user.setStatus(newUser.getStatus());

        uDao.updateByID(user.getUserId(), user);
        uDao.updateUserHasRole(user.getUserId(), user.getUserRole());
    }

    @Override
    public List<User> searchByNameASC(String name) {
        List<User> users = uDao.searchByName(name);
        
        Collections.sort(users, (o1, o2)
                -> (o1.getLastName() + " " + o1.getFirstName()).compareToIgnoreCase(o2.getLastName() + " " + o2.getFirstName()));
        return users;
    }
    
    @Override
    public List<User> searchByNameDESC(String name) {
        List<User> users = uDao.searchByName(name);
        
        Collections.sort(users, (o1, o2)
                -> (o2.getLastName() + " " + o2.getFirstName()).compareToIgnoreCase(o1.getLastName() + " " + o1.getFirstName()));
        return users;
    }

    @Override
    public List<User> searchByEmailASC(String email) {
        List<User> users = uDao.searchByEmail(email);
        //do sort
        //Collator collator = Collator.getInstance(Locale.FRENCH);
        Collections.sort(users, (o1,o2)-> o1.getEmail().compareToIgnoreCase(o2.getEmail())
        );
        return users;
    }
    
    @Override
    public List<User> searchByEmailDESC(String email) {
        List<User> users = uDao.searchByEmail(email);
        //do sort
        //Collator collator = Collator.getInstance(Locale.FRENCH);
        Collections.sort(users, (o1,o2)-> o2.getEmail().compareToIgnoreCase(o1.getEmail())
        );
        return users;
    }
    
    @Override
    public List<User> searchByNameAndRoleAndOrderByNameASC(String name, String roleName) {
        List<User> users = uDao.searchByNameAndRole(name, roleName);
        //do sort
        //Collator collator = Collator.getInstance(Locale.FRENCH);
        Collections.sort(users, (o1, o2)
                -> (o1.getLastName() + " " + o1.getFirstName()).compareToIgnoreCase(o2.getLastName() + " " + o2.getFirstName()));
        return users;
    }
    @Override
    public List<User> searchByNameAndRoleAndOrderByNameDESC(String name, String roleName) {
        List<User> users = uDao.searchByNameAndRole(name, roleName);
        //do sort
        Collections.sort(users, (o1, o2)
                -> (o2.getLastName() + " " + o2.getFirstName()).compareToIgnoreCase(o1.getLastName() + " " + o1.getFirstName()));
        return users;
    }

    @Override
    public List<User> searchByEmailAndRoleAndOrderByNameASC(String email, String roleName) {
        List<User> users = uDao.searchByEmailAndRole(email, roleName);
        //do sort
        //Collator collator = Collator.getInstance(Locale.FRENCH);
        Collections.sort(users, (o1,o2)-> o1.getEmail().compareToIgnoreCase(o2.getEmail())
        );
        return users;
    }

    @Override
    public List<User> searchByEmailAndRoleAndOrderByNameDESC(String email, String roleName) {
        
        List<User> users = uDao.searchByEmailAndRole(email, roleName);
        //do sort
        //Collator collator = Collator.getInstance(Locale.FRENCH);
        Collections.sort(users, (o1,o2)-> o2.getEmail().compareToIgnoreCase(o1.getEmail())
        );
        return users;
    }
}
