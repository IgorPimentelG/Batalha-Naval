package recursos.email;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

public class EmailSetup {

    private String remetente 	= "cinemonteiro.ads@gmail.com";
    private String password 	= "d=uYu3wu";

    public MimeMessage setup() {

        // ConfiguraÃ§Ã£o Gmail
        Properties props = new Properties();
        props.put("mail.smtp.user", this.remetente);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smpt.port", 25);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.EnableSSL,enable", "true");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");

        // AutenticaÃ§Ã£o E-mail
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(remetente, password);
            }
        });

        return new MimeMessage(session);

    }

    protected String getRemetente() {
        return remetente;
    }

}