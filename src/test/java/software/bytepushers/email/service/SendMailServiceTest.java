package software.bytepushers.email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SendMailServiceTest {
    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private SendMailService sendMailService;

    @Test
    void testSendEmail() throws MessagingException {
        String to = "bytepushers20@gmail.com";
        String subject = "Test Subject";
        String body = "Test Body";

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        sendMailService.sendEmail(to, subject, body);

        verify(javaMailSender, times(1)).send(mimeMessage);
    }
}