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

        String expectedEmailRecipient = "bytepushers20@gmail.com";
        String expectedEmailSubject = "Test Subject";
        String expectedEmailBody = "Test Body";
        Boolean expectedHtmlBody = Boolean.valueOf("This is unit testing for service layer");

        String actualEmailRecipient = "bytepushers20@gmail.com";
        //String actualFrom = "bytepushers20@gmail.com";
        String actualEmailSubject = "Test Subject";
        String actualEmailBody = "Test Body";
        Boolean actualHtmlBody = Boolean.valueOf("This is unit testing for service layer");


        sendMailService.sendEmail(expectedEmailRecipient, expectedEmailSubject, expectedEmailBody, expectedHtmlBody);

        verify(javaMailSender, times(1)).send(mimeMessage);

        assertEquals(expectedEmailRecipient, actualEmailRecipient);
        assertEquals(expectedEmailSubject, actualEmailSubject);
        assertEquals(expectedEmailBody, actualEmailBody);
        assertEquals(expectedHtmlBody, actualHtmlBody);
    }

    @Test
    void testSendEmail() throws MessagingException {
        String expectedTo = "bytepushers20@gmail.com";
        String expectedFrom = "bytepushers20@gmail.com";
        String expectedHtmlSubject = "HTML test email";
        String expectedHtmlBody = "<html><body><h1>Hello John </h1> from html email!!!</body></html>";

        sendMailService.sendEmail();

        verify(javaMailSender, times(1)).send(mimeMessage);

        verify(mimeMessage).setFrom(new InternetAddress(expectedFrom));
        verify(mimeMessage).setRecipients(MimeMessage.RecipientType.TO, expectedTo);
        verify(mimeMessage).setSubject(expectedHtmlSubject);
        verify(mimeMessage).setContent(expectedHtmlBody, "text/html; charset=utf-8");
    }
}
