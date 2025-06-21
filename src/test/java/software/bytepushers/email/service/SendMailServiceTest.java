package software.bytepushers.email.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SendMailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private ResourceLoader resourceLoader;

    @InjectMocks
    private SendMailService sendMailService;

    @Test
    void testSendEmail() throws MessagingException {
        String expectedSender = "bytepushers20@gmail.com";
        String expectedRecipient = "bytepushers20@gmail.com";
        String expectedSubject = "Test Subject";
        String expectedBody = "Test Body";
        boolean expectedHtmlBody = true;

        String actualSender = "bytepushers20@gmail.com";
        String actualRecipient = "bytepushers20@gmail.com";
        String actualSubject = "Test Subject";
        String actualBody = "Test Body";
        boolean actualHtmlBody = true;

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        sendMailService.sendEmail(actualSender, actualRecipient, actualSubject, actualBody, actualHtmlBody);

        ArgumentCaptor<MimeMessage> mimeMessageCaptor = ArgumentCaptor.forClass(MimeMessage.class);
        verify(javaMailSender).send(mimeMessageCaptor.capture());

        assertEquals(expectedSender, actualSender);
        assertEquals(expectedRecipient, actualRecipient);
        assertEquals(expectedSubject, actualSubject);
        assertEquals(expectedBody, actualBody);
        assertEquals(expectedHtmlBody, actualHtmlBody);
    }
    @Test
    public void testEmailTemplate() throws IOException {
        // Arrange
        Resource mockResource = mock(Resource.class);
        when(resourceLoader.getResource("classpath:templates/email/hello-world.html")).thenReturn(mockResource);
        String expectedTemplateContent = "Expected Email Template Content";
        when(mockResource.getInputStream()).thenReturn(IOUtils.toInputStream(expectedTemplateContent, StandardCharsets.UTF_8));

        String actualTemplateContent = sendMailService.emailTemplate("hello-world.html");
        assertEquals(expectedTemplateContent, actualTemplateContent);
    }
}

