package util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * include method of encrypting the password
 *
 * @author manhphong
 */
public class PasswordEncryption {

    /**
     * use SHA-256 algorithm to encrypt the password
     *
     * @param pass
     * @return password encrypted
     */
    public static String doSha256(String pass) {
        pass = pass + "asdbasdsadbsnadnqnsdad17sadjas3@@sdh5";
        String result = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] messageDigest = md.digest(pass.getBytes("UTF-8"));

            BigInteger bigInt = new BigInteger(1, messageDigest);

            result = bigInt.toString(16);
            while (result.length() < 40) {
                result = "0" + result;
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PasswordEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(PasswordEncryption.doSha256("123456"));
    }
}
