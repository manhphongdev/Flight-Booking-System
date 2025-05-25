
package service.serviceimpl;

import dao.IUserDAO;
import dao.daoimpl.UserDAOImpl;
import enums.UserStatus;
import java.util.Optional;
import model.UserEntity;
import service.IUserService;

/**
 *
 * @author manhphong
 */
public class UserServiceImpl implements IUserService {

    private IUserDAO uDao;

    public UserServiceImpl() {
        this.uDao = new UserDAOImpl();
    }

    @Override
    public Long register(UserEntity user) {
        if (isUserExist(user)) {
            return null;
        } else {
            user.setStatus(UserStatus.PENDING);
            return uDao.insert(user);
        }
    }

    public boolean isUserExist(UserEntity user) {
        Optional<UserEntity> userCheckExist = uDao.findByEmail(user.getEmail());
        return userCheckExist.isPresent();
    }

    @Override
    public boolean loginAdmin(String email, String password) {
        
    }

}
