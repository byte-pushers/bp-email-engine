package software.bytepushers.email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {

    @Autowired
    private JavaMailSender javaMailSender;

//    public void sendEmail(String toEmail, String subject, String body) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(toEmail);
//        message.setSubject(subject);
//        message.setText(body);
//        javaMailSender.send(message);
//    }

    public SendMailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendHtlmEmail() throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.setFrom(new InternetAddress("bytepushers20@gmail.com"));
        message.setRecipients(MimeMessage.RecipientType.TO, "bytepushers20@gmail.com");
        message.setSubject("HTML test email");

        String html =
                "<html><body>" +
                        "<h1>Hello John </h1> from html email!!!" +
                        "</body>" +
                        "</html>";

        message.setContent(html, "text/html; charset=utf-8");
        javaMailSender.send(message);
    }
}
