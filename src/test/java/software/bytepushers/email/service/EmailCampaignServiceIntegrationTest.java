package software.bytepushers.email.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
public class EmailCampaignServiceIntegrationTest {
        @Autowired
        private EmailCampaignService emailCampaignService;

        @Autowired
        private SendMailService sendMailService;

        private Workbook workbook;
        private MultipartFile file;

        @BeforeEach
        void setUp() throws Exception {
            // Creating an in-memory Excel file with recipient data
            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet1");
            Row dataRow = sheet.createRow(1);
            dataRow.createCell(0).setCellValue("Byte pushers");
            dataRow.createCell(1).setCellValue("bytepushers20@gmail.com");

            // Convert workbook to a file
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

            file = new MockMultipartFile("file", "test.xlsx", "application/vnd.ms-excel", inputStream.readAllBytes());
        }

        @Test
        void testStartCampaign() throws Exception {
            emailCampaignService.startCampaign(file);

            verify(sendMailService, times(1)).sendEmail(
                    "bytepushers20@gmail.com", "bytepushers20@gmail.com", "Hello John Doe", "templates/email/hello-world.html", true
            );
        }

        @AfterEach
        void tearDown() throws Exception {
            workbook.close();
        }
}
