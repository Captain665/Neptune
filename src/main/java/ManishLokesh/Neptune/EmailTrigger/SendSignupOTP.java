package ManishLokesh.Neptune.EmailTrigger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendSignupOTP {

    public void sendOTP(String receiver, String otp ,String subject, String text){
        String host = "smtp.gmail.com";
        String sender  = "manishneptune201@gmail.com";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host",host);
        properties.setProperty("mail.smtp.auth","true");
        properties.setProperty("mail.smtp.ssl.enable","true");
        properties.setProperty("mail.smtp.port","465");
        properties.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");


        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(sender,"vweuedgbstlxgcvk");
                    }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(receiver));
            message.setSubject(subject);
            message.setText(text + otp);

            Transport.send(message);
        }catch (MessagingException max){
            max.printStackTrace();
        }
    }
}
