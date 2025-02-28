package software.bytepushers.email.controller;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import software.bytepushers.email.service.SendMailService;

import java.util.Date;

@RestController
public class MailController {
    @Autowired
    private SendMailService sendMailService;
    @GetMapping("sendEmail")

    private void Run() throws MessagingException{
        sendMailService.sendHtlmEmail();
    }
}
