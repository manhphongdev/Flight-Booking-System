package model;

import enums.Gender;
import enums.Role;
import enums.UserStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserEntity {

    private long userId;
    private String email;
    private String password;
    private Role role;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phone;
    private String nationlity; //quoc tich
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;
    private UserStatus status;

    public UserEntity() {
    }

    public UserEntity(long userId, String email, String password, Role role, String firstName, String lastName, LocalDate dateOfBirth, Gender gender, String phone, String nationlity, LocalDateTime createAt, LocalDateTime updatedAt, LocalDateTime lastLogin, UserStatus status) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phone = phone;
        this.nationlity = nationlity;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.lastLogin = lastLogin;
        this.status = status;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNationlity() {
        return nationlity;
    }

    public void setNationlity(String nationlity) {
        this.nationlity = nationlity;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserEntity{" + "userId=" + userId + ", email=" + email + ", password=" + password + ", role=" + role + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", phone=" + phone + ", nationlity=" + nationlity + ", createAt=" + createAt + ", updatedAt=" + updatedAt + ", lastLogin=" + lastLogin + ", status=" + status + '}';
    }

}
