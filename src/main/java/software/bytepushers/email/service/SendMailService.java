package software.bytepushers.email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendHtlmEmail() throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.setFrom(new InternetAddress("bytepushers20@gmail.com"));
        message.setRecipients(MimeMessage.RecipientType.TO, "bytepushers20@gmail.com");
        message.setSubject("HTML test email");

        String html =
                "<html><body>" +
                        "Hello from html email!!!" +
                        "</body>" +
                        "</html>";

        message.setContent(html, "text/html; charset=utf-8");
        javaMailSender.send(message);

    }
}
