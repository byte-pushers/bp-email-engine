package software.bytepushers.email.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmailCampaignServiceTest {

    @Mock
    private SendMailService sendMailService;

    @Mock
    private MultipartFile file;

    @InjectMocks
    private EmailCampaignService emailCampaignService;

    @Test
    void testStartCampaign() throws Exception {
        // Expected values
        String expectedSender = "bytepushers20@gmail.com";
        String expectedName = "Bytepushers";
        String expectedRecipient = "bytepushers20@gmail.com";
        String expectedSubject = "Hello Bytepushers";
        String expectedHtmlTemplate = "templates/email/hello-world.html";
        boolean expectedHtmlBody = true;

        // Actual values
        String actualSender = "bytepushers20@gmail.com";
        String actualName = "Bytepushers";
        String actualRecipient = "bytepushers20@gmail.com";
        String actualSubject = "Hello Bytepushers";
        String actualHtmlTemplate = "templates/email/hello-world.html";
        //boolean actualHtmlBody = true;

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        Row headerRow = sheet.createRow(0);
        Row dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue(expectedName);
        dataRow.createCell(1).setCellValue(expectedRecipient);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        when(file.getInputStream()).thenReturn(inputStream);

        emailCampaignService.startCampaign(file);

        verify(sendMailService, times(1)).sendEmail(
                expectedSender, expectedRecipient, expectedSubject, expectedHtmlTemplate, expectedHtmlBody);


        assertEquals(expectedSender, actualSender);
        assertEquals(expectedName, actualName);
        assertEquals(expectedRecipient, actualRecipient);
        assertEquals(expectedSubject, actualSubject);
        assertEquals(expectedHtmlTemplate, actualHtmlTemplate);
        assertEquals(expectedHtmlBody, true);

        workbook.close();
    }
}
