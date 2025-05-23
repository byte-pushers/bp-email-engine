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
