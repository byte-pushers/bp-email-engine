package software.bytepushers.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import software.bytepushers.email.service.EmailCampaignService;

@RestController
@RequestMapping("/email")
public class MailController {

    @Autowired
    private EmailCampaignService emailCampaignService;

    @PostMapping("/uploadExcel")
    public String uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            emailCampaignService.startCampaign(file);
            return "Email sent successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Email sent failed" + e.getMessage();
        }
    }
}

//    @Autowired
//    private SendMailService sendMailService;
//
//    @PostMapping("/sendEmail")
//    public String sendEmail(@RequestBody MailRequest mailRequest){
//        try {
//            sendMailService.sendEmail(mailRequest.getTo(), mailRequest.getSubject(), mailRequest.getBody());
//            return "Email sent";
//        } catch (MessagingException e){
//            return "email not sent" + e.getMessage();
//        }
//    }
//
//    @PostMapping("htmlEmail")
//    private void Run() throws MessagingException {
//        sendMailService.sendHtlmEmail();
//        System.out.println("Sent successfully");
//    }
//
//    @PostMapping("/uploadExcel")
//    public String uploadExcel(@RequestParam("file") MultipartFile file) throws MessagingException {
//        try{
//            sendMailService.sendEmailFromExcel(file);
//            return "Email sent successfully";
//        }catch (Exception e){
//            return "email not sent" + e.getMessage();
//        }
//    }
//
//    @PostMapping("/send")
//    public String send(@RequestParam("file") MultipartFile file, @RequestBody String campaignName) throws MessagingException {
//        //TODO: inject EmailCampaignService either in constructor or as class attribute.
//        EmailCampaignService.start(file, campaignName);
//    }
//}
