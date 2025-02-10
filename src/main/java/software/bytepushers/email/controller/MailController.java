package software.bytepushers.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import software.bytepushers.email.service.SendMailService;

@RestController
public class MailController {
    @Autowired
    private SendMailService sendMailService;
    @GetMapping("sendEmail")
    public String sendEmail() {
        sendMailService.sendMail("tonte.pouncil@gmail.com", "Test Email", "Hello! This is a demo email for our Bytepushers flagship email project.");
        return "Sent successfully";
    }
}
