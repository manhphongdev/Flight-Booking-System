package config.email;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.DataSource;
import javax.activation.DataHandler;

/**
 *
 * @author manhphong
 * @version 1.0
 */
public class EmailConfig {

    public static final String HOST_NAME = "smtp.gmail.com";
    public static final int SSL_PORT = 465;
    public static final int TSL_PORT = 587;

    public static String APP_EMAIL = "phongtm1703@gmail.com";
    public static String APP_PASSWORD = "caeadlfwkhdiyung";
    public static final String RECEIVE_EMAIL = "phongtm1703@gmail.com";

    public static void sendEmail(String content, String sendTo) {
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST_NAME);
        props.put("mail.smtp.port", TSL_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        //create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(APP_EMAIL, APP_PASSWORD);
            }
        };

        // thong qua getInstance de co the dang nhap vao gmail
        // thong qua session de co the lam viec voi email
        Session session = Session.getInstance(props, auth);

        //send email
        //create a message
        MimeMessage msg = new MimeMessage(session);

        try {
            // content Type
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            // from
            msg.setFrom(APP_EMAIL);
            // 
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(sendTo, false));
            // content email
            msg.setSubject("Xác thực tài khoản","UTF-8");
            msg.setSentDate(new Date());
            // main content
            msg.setContent(content, "text/html; charset=UTF-8");
            Transport.send(msg);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
