package software.bytepushers.email.service;

import org.springframework.web.multipart.MultipartFile;

public class EmailCampaignService {

    public EmailCampaignService() {

    }

    // TODO: Throw our own Application Exception/  Global Error Handler
    public void start(String campaignName, MultipartFile file) {
        // TODO: use the EXCEL SERVICE/UTILITY CLASS to process file and loop through file
        // TODO: use the Send Mail Service to send email per loop.
    }


}
