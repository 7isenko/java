import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {

    private static String host = "smtp.mail.ru";
    private static String username = "it_is_smtp_baby@mail.ru";
    private static String password = "itmoisbest123123";
    private static int smtp_port = 465; // 465 / 25

    public static void sendPassword(String email, String pswd) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", smtp_port);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Password for lab!!!");
            message.setText("There is your password: " + pswd);
            try{
                Transport.send(message); }
            catch (SendFailedException e) {
                System.out.println("But this email is does not exist");
            }
            System.out.println("Sent message successfully! user's password is " + pswd);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
