package software.bytepushers.email.controller;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.bytepushers.email.dto.MailRequest;
import software.bytepushers.email.service.SendMailService;

import java.util.Date;

@RestController
@RequestMapping("email")
public class MailController {
    @Autowired
    private SendMailService sendMailService;

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody MailRequest mailRequest){
        try {
            sendMailService.sendEmail(mailRequest.getTo(), mailRequest.getSubject(), mailRequest.getBody());
            return "Email sent";
        } catch (MessagingException e){
            return "email not sent" + e.getMessage();
        }
    }

    @PostMapping("htmlEmail")
    private void Run() throws MessagingException {
        sendMailService.sendHtlmEmail();
        System.out.println("Sent successfully");
    }

    @PostMapping("/uploadExcel")
    public String uploadExcel(@RequestParam("file") MultipartFile file) throws MessagingException {
        try{
            sendMailService.sendEmailFromExcel(file);
            return "Email sent successfully";
        }catch (Exception e){
            return "email not sent" + e.getMessage();
        }
    }

    @PostMapping("/send")
    public String send(@RequestParam("file") MultipartFile file, @RequestBody String campaignName) throws MessagingException {
        //TODO: inject EmailCampaignService either in constructor or as class attribute.
        EmailCampaignService.start(file, campaignName);
    }
}
