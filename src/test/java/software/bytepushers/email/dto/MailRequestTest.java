package software.bytepushers.email.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MailRequestTest {
    @Test
    void testMailRequestGettersAndSetters() {
        String expectedTo = "to@example.com";
        String expectedSubject = "Subject";
        String expectedBody = "Body";

        String actualTo = "to@example.com";
        String actualSubject = "Subject";
        String actualBody = "Body";

        MailRequest mailRequest = new MailRequest();
        mailRequest.setTo(actualTo);
        mailRequest.setSubject(actualSubject);
        mailRequest.setBody(actualBody);

        assertEquals(expectedTo, mailRequest.getTo());
        assertEquals(expectedSubject, mailRequest.getSubject());
        assertEquals(expectedBody, mailRequest.getBody());
    }
}
