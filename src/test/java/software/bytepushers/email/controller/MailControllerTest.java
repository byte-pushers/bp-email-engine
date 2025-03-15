package software.bytepushers.email.controller;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import software.bytepushers.email.dto.MailRequest;
import software.bytepushers.email.service.SendMailService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class MailControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SendMailService sendMailService;

    @InjectMocks
    private MailController mailController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(mailController).build();
    }

    @Test
    void testSendEmail_Success() throws Exception {
        MailRequest mailRequest = new MailRequest();
        mailRequest.setTo("bytepushers20@gmail.com");
        mailRequest.setSubject("Test Subject");
        mailRequest.setBody("Test Body");

        doNothing().when(sendMailService).sendEmail(mailRequest.getTo(), mailRequest.getSubject(), mailRequest.getBody());

        mockMvc.perform(post("/email/sendEmail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mailRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("Email sent"));

        verify(sendMailService, times(1)).sendEmail(mailRequest.getTo(), mailRequest.getSubject(), mailRequest.getBody());
    }

    @Test
    void testSendEmail_Failure() throws Exception {
        MailRequest mailRequest = new MailRequest();
        mailRequest.setTo("bytepushers20@gmail.com");
        mailRequest.setSubject("Test Subject");
        mailRequest.setBody("Test Body");

        doThrow(new MessagingException("SMTP error")).when(sendMailService).sendEmail(anyString(), anyString(), anyString());

        mockMvc.perform(post("/email/sendEmail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mailRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("email not sentSMTP error"));

        verify(sendMailService, times(1)).sendEmail(anyString(), anyString(), anyString());
    }
}
