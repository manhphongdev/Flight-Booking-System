package service.impl;

import controller.usercontroller.UserRegister;
import dao.UserDAOImpl;
import dto.UserRegisterRequest;
import enums.Role;
import enums.UserStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import model.UserEntity;
import service.UserService;
import util.PasswordEncryption;

public class UserServiceImpl implements UserService {

    private UserDAOImpl userDAO;
    private static final Logger logger = Logger.getLogger(UserRegister.class.getName());

    public UserServiceImpl() {
        this.userDAO = new UserDAOImpl();
    }

    @Override
    public long register(UserRegisterRequest userRq) {
        // check email exist

        UserEntity userEntity = new UserEntity();

        //userEntity.setPassword(PasswordEncryption.doSha256(userEntity.getPassword()));
        userEntity.setFirstName(userRq.getFirstName());
        userEntity.setLastName(userRq.getLastName());
        userEntity.setEmail(userRq.getEmail());
        userEntity.setPassword(PasswordEncryption.doSha256(userRq.getPassword()));

        userEntity.setRole(Role.CUSTOMER);
        userEntity.setCreateAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());
        userEntity.setLastLogin(LocalDateTime.now());
        userEntity.setStatus(UserStatus.PENDING);

        logger.info("before ins service: " + userEntity.toString());
        boolean result = userDAO.insert(userEntity);
        logger.info("Insert result: " + result);
        logger.info("after ins service: " + userEntity.toString());
        return userEntity.getUserId();
    }

    @Override
    public boolean isEmailExist(String email) {
        return userDAO.isEmailExist(email);
    }

    @Override
    public UserEntity getUserByID(long id) {
        return null;

    }

    @Override
    public Optional<UserEntity> getUserbyEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public boolean login(UserEntity u) {

        boolean login = false;

        Optional<UserEntity> user = userDAO.findByEmailAndPassword(u.getEmail(), PasswordEncryption.doSha256(u.getPassword()));
        if (user.isPresent()) {
            login = true;
        }
        return login;
    }

    @Override
    public List<UserEntity> getAllUser() {
        return userDAO.findAll();
    }

    public static void main(String[] args) {
        UserService u = new UserServiceImpl();
        List<UserEntity> users = u.getAllUser();
        for (UserEntity user : users) {
            System.out.println("User: " + user);
        }
    }
}
