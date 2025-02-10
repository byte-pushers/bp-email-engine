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
}
