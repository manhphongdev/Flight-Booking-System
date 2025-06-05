package model;

import enums.UserStatus;
import java.time.LocalDateTime;

/**
 *
 * @author manhphong
 * @version 1.0
 */
public class User {

    private Long userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginedAt;
    private UserStatus status;
    private String userRole;

    public User() {
    }

    public User(Long userId, String email, String firstName, String lastName, LocalDateTime lastLoginedAt, UserStatus status) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastLoginedAt = lastLoginedAt;
        this.status = status;
    }

    

    public User(String email, String password, String firstName, String lastName, String userRole) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
    }

    public User(Long userId, String email, String firstName, String lastName, LocalDateTime lastLoginedAt, UserStatus status, String userRole) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastLoginedAt = lastLoginedAt;
        this.status = status;
        this.userRole = userRole;
    }

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(Long userId, String email, String password, String firstName, String lastName, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime lastLoginedAt, UserStatus status) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastLoginedAt = lastLoginedAt;
        this.status = status;
    }

    public User(Long userId, String email, String password, String firstName, String lastName, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime lastLoginedAt, UserStatus status, String userRole) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastLoginedAt = lastLoginedAt;
        this.status = status;
        this.userRole = userRole;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getLastLoginedAt() {
        return lastLoginedAt;
    }

    public void setLastLoginedAt(LocalDateTime lastLoginedAt) {
        this.lastLoginedAt = lastLoginedAt;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserEntity{" + "userId=" + userId + ", email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", lastLoginedAt=" + lastLoginedAt + ", status=" + status + ", userRole=" + userRole + '}';
    }

}
