/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.time.LocalDateTime;
import java.util.Optional;
import model.EmailAuthentication;

/**
 *
 * @author manhphong
 * @version 1.0
 */
public interface IEmailAuthenticationDAO {

    public Long insertVerify(Long userID, String token, LocalDateTime expiredAt, String status);

    public boolean updateEmailAuthentication(Long id, EmailAuthentication entity);

    public Optional<EmailAuthentication> getEmailAuthencication(Long userId);
}
