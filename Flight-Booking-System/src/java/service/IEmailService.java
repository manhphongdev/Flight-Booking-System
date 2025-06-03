
package service;

import model.UserEntity;

/**
 *
 * @author manhphong
 */
public interface IEmailService {
    public void hanlderRequestVerify(String token, UserEntity entity);
}
