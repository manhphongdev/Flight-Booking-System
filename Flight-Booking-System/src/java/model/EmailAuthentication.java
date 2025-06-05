package model;

import java.time.LocalDateTime;

/**
 *
 * @author manhphong
 * @version 1.0
 */
public class EmailAuthentication {

    private Long id;
    private Long userid;
    private String token;
    private LocalDateTime expiredAt;
    private String status;

    public EmailAuthentication() {
    }

    public EmailAuthentication(Long userid, String token, LocalDateTime expiredAt, String status) {
        this.userid = userid;
        this.token = token;
        this.expiredAt = expiredAt;
        this.status = status;
    }

    public EmailAuthentication(Long id, Long userid, String token, LocalDateTime expiredAt, String status) {
        this.id = id;
        this.userid = userid;
        this.token = token;
        this.expiredAt = expiredAt;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(LocalDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EmailAuthentication{" + "id=" + id + ", userid=" + userid + ", token=" + token + ", expiredAt=" + expiredAt + ", status=" + status + '}';
    }

}
