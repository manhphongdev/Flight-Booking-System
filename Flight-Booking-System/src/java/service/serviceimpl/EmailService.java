package service.serviceimpl;

import dao.IEmailAuthenticationDAO;
import dao.daoimpl.EmailAuthenticationDAOImpl;
import dao.daoimpl.UserDAOImpl;
import enums.UserStatus;
import exception.EntityNotFoundException;
import exception.InvalidTokenException;
import exception.TokenHasExpireException;
import java.time.LocalDateTime;
import model.EmailAuthentication;
import model.User;
import service.interfaces.IEmailService;

/**
 *
 * @author manhphong
 */
public class EmailService implements IEmailService {

    private final IEmailAuthenticationDAO verifyDAO;
    private final UserDAOImpl uDao;

    public EmailService() {
        this.verifyDAO = new EmailAuthenticationDAOImpl();
        uDao = new UserDAOImpl();
    }

    @Override
    public void hanlderRequestVerify(String token, User entity) {
        EmailAuthentication emailAuth = verifyDAO.getEmailAuthencication(entity.getUserId()).orElse(null);
        if (emailAuth == null) {
            throw new EntityNotFoundException("EmailAuthentication not found!");
        }

        if (!emailAuth.getToken().equals(token)) {
            throw new InvalidTokenException("Token not exist! Verify failed!");
        }

        if ("ACTIVE".equals(emailAuth.getStatus())) {
            return;
        }
        if (emailAuth.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new TokenHasExpireException("Token has expired!");
        }

        try {
            // Update email authentication status
            emailAuth.setStatus(UserStatus.ACTIVE.toString());
            boolean emailAuthUpdated = verifyDAO.updateEmailAuthentication(entity.getUserId(), emailAuth);
            if (!emailAuthUpdated) {
            }

            // Update user status
            entity.setStatus(UserStatus.ACTIVE);
            uDao.updateByID(entity.getUserId(), entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
