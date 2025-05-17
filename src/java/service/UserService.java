package service;

import dto.UserRegisterRequest;
import java.util.List;
import java.util.Optional;
import model.UserEntity;

public interface UserService {

    /**
     * register new user
     *
     * @return id of new user
     */
    public long register(UserRegisterRequest user);

    /**
     * get user entity by id
     *
     * @param id
     * @return user entity
     */
    public UserEntity getUserByID(long id);

    /**
     * check email exist db
     * @param email
     * @return true if email exist 
     */
    boolean isEmailExist(String email);

    /**
     * find user by email in database
     * @param email
     * @return UserEntity object
     */
    public Optional<UserEntity> getUserbyEmail(String email);

    /**
     * login for user, admin
     * @param u
     * @return true if email and password are correct
     */
    public boolean login(UserEntity u);
    
    /**
     * get all of users form database
     * @return list UserEntity
     */
    public List<UserEntity> getAllUser();
    

}
