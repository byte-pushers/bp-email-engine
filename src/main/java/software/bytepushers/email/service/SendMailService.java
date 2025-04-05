package software.bytepushers.email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

@Service
public class SendMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private static final String HTML_TEMPLATE = "";  //TODO: read html from html file.

    // TODO: REMOVE METHOD
    public void sendEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("bytepushers20@gmail.com");
        javaMailSender.send(mimeMessage);
    }

    // TODO: REMOVE METHOD
    public void sendHtmlEmail(String to, String subject, String htmlContent) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        helper.setFrom("bytepushers20@gmail.com");
        javaMailSender.send(mimeMessage);
    }

    // TODO: REMOVE METHOD
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

    // TODO: PUT ALL EXCEL LOGIC INTO EXCEL SERVICE/UTILITY CLASS
    public void sendEmailFromExcel(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        
        if(rowIterator.hasNext()) rowIterator.next();
        
        while(rowIterator.hasNext()){
            Row row = rowIterator.next();
            String name = row.getCell(0).getStringCellValue();
            String email = row.getCell(1).getStringCellValue();
            
            String personalizedHtml = HTML_TEMPLATE
                    .replace("{{name}}", name)
                    .replace("{{email}}", email);

            sendHtmlEmail(email, "Personalied Email", personalizedHtml);
        }
        workbook.close();
    }


    public void sendEmail(String emailSender, String emailRecipient, String emailSubject, String emailBody, Boolean htmlBody) throws MessagingException {
        // TODO: Only put email logic here.  This method is only responsible for one thing - sending email.
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, htmlBody);
        helper.setTo(emailRecipient);
        helper.setSubject(emailSubject);
        helper.setText(emailBody, htmlBody);
        helper.setFrom(emailSender);
        javaMailSender.send(mimeMessage);
    }
}
