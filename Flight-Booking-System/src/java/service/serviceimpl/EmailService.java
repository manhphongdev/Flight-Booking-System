package service.serviceimpl;

import dao.IEmailAuthenticationDAO;
import dao.daoimpl.EmailAuthenticationDAOImpl;
import enums.UserStatus;
import exception.EntityNotFoundException;
import exception.InvalidTokenException;
import exception.TokenHasExpireException;
import java.time.LocalDateTime;
import model.EmailAuthentication;
import model.UserEntity;
import service.IEmailService;

/**
 *
 * @author manhphong
 */
public class EmailService implements IEmailService{

    private final IEmailAuthenticationDAO verifyDAO;

    public EmailService() {
        this.verifyDAO = new EmailAuthenticationDAOImpl();
    }
    
    @Override
    public void hanlderRequestVerify(String token, UserEntity entity) {
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
        emailAuth.setStatus(UserStatus.ACTIVE.toString());
        
        verifyDAO.updateEmailAuthentication(entity.getUserId(), emailAuth);
    }
}
