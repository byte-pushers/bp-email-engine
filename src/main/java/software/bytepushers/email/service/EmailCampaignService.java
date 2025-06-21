package software.bytepushers.email.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Iterator;

@Service
public class EmailCampaignService {

    @Autowired
    private SendMailService sendMailService;

    private static final String HTML_TEMPLATE = "templates/email/hello-world.html";

    public void startCampaign(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        if(rowIterator.hasNext()) rowIterator.next();

        //String htmlTemplate = sendMailService.emailTemplate(HTML_TEMPLATE);

        while(rowIterator.hasNext()){
            Row row = rowIterator.next();
            String name = row.getCell(0).getStringCellValue();
            String email = row.getCell(1).getStringCellValue();

            String personalizedHtml = HTML_TEMPLATE
                    .replace("{{name}}", name)
                    .replace("{{email}}", email);
            String subject = "Hello " + name;
            sendMailService.sendEmail("bytepushers20@gmail.com", email, subject, personalizedHtml, true);
        }
        workbook.close();
    }
}
