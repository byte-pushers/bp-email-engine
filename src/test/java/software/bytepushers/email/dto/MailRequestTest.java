package software.bytepushers.email.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MailRequestTest {
    @Test
    void testMailRequestGettersAndSetters() {
        MailRequest mailRequest = new MailRequest();
        mailRequest.setTo("bytepushers20@gmail.com");
        mailRequest.setSubject("Test Subject");
        mailRequest.setBody("Test Body");

        assertEquals("bytepushers20@gmail.com", mailRequest.getTo());
        assertEquals("Test Subject", mailRequest.getSubject());
        assertEquals("Test Body", mailRequest.getBody());
    }
}
