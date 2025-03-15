package software.bytepushers.email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("bytepushers20@gmail.com");
        javaMailSender.send(mimeMessage);
    }

    public void sendHtlmEmail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        mimeMessage.setFrom(new InternetAddress("bytepushers20@gmail.com"));
        mimeMessage.setRecipients(MimeMessage.RecipientType.TO, "bytepushers20@gmail.com");
        mimeMessage.setSubject("HTML test email");
        String html =
                "<html><body>" +
                        "<h1>Hello John </h1> from html email!!!" +
                "</body>" +
                        "</html>";
        mimeMessage.setContent(html, "text/html; charset=utf-8");
        javaMailSender.send(mimeMessage);
    }
}
