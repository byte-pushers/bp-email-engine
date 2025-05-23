package software.bytepushers.email.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import software.bytepushers.email.service.EmailCampaignService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MailControllerTest {

    @Mock
    private EmailCampaignService emailCampaignService;

    @InjectMocks
    private MailController mailController;

    private MultipartFile mockFile;

    @BeforeEach
    public void setup() {
        mockFile = new MockMultipartFile(
                "file",
                "test.xlsx",
                MediaType.APPLICATION_OCTET_STREAM_VALUE,
                new byte[]{}
        );
    }

    @Test
    public void testUploadExcel_Success() throws Exception {
        // Arrange
        doNothing().when(emailCampaignService).startCampaign(mockFile);

        // Act
        String response = mailController.uploadExcel(mockFile);

        // Assert
        assertEquals("Email sent successfully", response);
        verify(emailCampaignService, times(1)).startCampaign(mockFile);
    }

    @Test
    public void testUploadExcel_Failure() throws Exception {
        // Arrange
        doThrow(new RuntimeException("File processing error")).when(emailCampaignService).startCampaign(mockFile);

        // Act
        String response = mailController.uploadExcel(mockFile);

        // Assert
        assertEquals("Email sent failedFile processing error", response);
        verify(emailCampaignService, times(1)).startCampaign(mockFile);
    }
}
