
package service.interfaces;

import model.User;

/**
 *
 * @author manhphong
 */
public interface IEmailService {
    public void hanlderRequestVerify(String token, User entity);
}
