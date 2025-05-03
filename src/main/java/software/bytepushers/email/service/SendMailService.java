package software.bytepushers.email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class SendMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ResourceLoader resourceLoader;

    public void sendEmail(String emailSender, String emailRecipient, String emailSubject, String emailBody, Boolean htmlBody) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, htmlBody);
        helper.setTo(emailRecipient);
        helper.setSubject(emailSubject);
        helper.setText(emailBody, htmlBody);
        helper.setFrom(emailSender);
        javaMailSender.send(mimeMessage);
    }

    public String emailTemplate(String emailTemplateName) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:templates/email/hello-world.html");
        return IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
    }


//    public String readHtmlFile(String fileName) throws IOException {
//        Resource resource = resourceLoader.getResource("classpath:" + fileName);
//        return IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
//    }
//
//
//    public String readFile(String)
//
//    private static final String HTML_TEMPLATE = "";  //TODO: read html from html file.
//
//    // TODO: REMOVE METHOD
//    public void sendEmail(String to, String subject, String body) throws MessagingException {
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(body);
//        message.setFrom("bytepushers20@gmail.com");
//        javaMailSender.send(mimeMessage);
//    }

//    // TODO: REMOVE METHOD
//    public void sendHtmlEmail(String to, String subject, String htmlContent) throws MessagingException {
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(htmlContent, true);
//        helper.setFrom("bytepushers20@gmail.com");
//        javaMailSender.send(mimeMessage);
//    }
//    // TODO: REMOVE METHOD
//    public void sendHtlmEmail() throws MessagingException {
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        mimeMessage.setFrom(new InternetAddress("bytepushers20@gmail.com"));
//        mimeMessage.setRecipients(MimeMessage.RecipientType.TO, "bytepushers20@gmail.com");
//        mimeMessage.setSubject("HTML test email");
//        String html =
//                "<html><body>" +
//                        "<h1>Hello John </h1> from html email!!!" +
//                "</body>" +
//                        "</html>";
//        mimeMessage.setContent(html, "text/html; charset=utf-8");
//        javaMailSender.send(mimeMessage);
//    }

}
