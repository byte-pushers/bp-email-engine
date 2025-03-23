package software.bytepushers.email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SendMailServiceIntegrationTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private SendMailService sendMailService;

    @Mock
    private MimeMessage mimeMessage;

    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
    }

    @AfterEach
    public void cleanUp() throws Exception {
        closeable.close();
    }

    @Test
    void varifyEmailWasSent() throws MessagingException {

        String expectedTo = "bytepushers20@gmail.com";
        String expectedSubject = "Test Subject";
        String expectedBody = "Test Body";

        String actualTo = "bytepushers20@gmail.com";
        String actualFrom = "bytepushers20@gmail.com";
        String actualSubject = "Test Subject";
        String actualBody = "Test Body";

        sendMailService.sendEmail(expectedTo, expectedSubject, expectedBody);

        verify(javaMailSender, times(1)).send(mimeMessage);

        assertEquals(expectedTo, actualTo);
        assertEquals(expectedSubject, actualSubject);
        assertEquals(expectedBody, actualBody);
    }

    @Test
    void testSendHtmlEmail() throws MessagingException {
        String expectedTo = "bytepushers20@gmail.com";
        String expectedFrom = "bytepushers20@gmail.com";
        String expectedHtmlSubject = "HTML test email";
        String expectedHtmlBody = "<html><body><h1>Hello John </h1> from html email!!!</body></html>";

        sendMailService.sendHtlmEmail();

        verify(javaMailSender, times(1)).send(mimeMessage);

        verify(mimeMessage).setFrom(new InternetAddress(expectedFrom));
        verify(mimeMessage).setRecipients(MimeMessage.RecipientType.TO, expectedTo);
        verify(mimeMessage).setSubject(expectedHtmlSubject);
        verify(mimeMessage).setContent(expectedHtmlBody, "text/html; charset=utf-8");

    }
}
