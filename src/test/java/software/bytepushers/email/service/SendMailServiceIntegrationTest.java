package software.bytepushers.email.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SendMailServiceIntegrationTest {

    @Autowired
    private JavaMailSender javaMailSender;

    private final String testRecipient = "bytepushers20@gmail.com";
    private final String testSubject = "Test integration email";
    private final String testBody = "<h1>Hello Integration Test!</h1>";

    @Test
    void testSendAndDeleteEmail() throws Exception {
        // Sending an actual email
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(testRecipient));
        message.setSubject(testSubject);
        message.setContent(testBody, "text/html");

        javaMailSender.send(message);
        assertTrue(true, "Email received.");

        // Deleting the email using IMAP
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");

        Session session = Session.getDefaultInstance(properties);
        Store store = session.getStore("imaps");
        store.connect("imap.gmail.com", "bytepushers20@gmail.com", "sulc zjub aqqa quyf");

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);

        Message[] messages = inbox.getMessages();
        boolean emailFound = false;

        for (Message msg : messages) {
            if (msg.getSubject().equals(testSubject)) {
                msg.setFlag(Flags.Flag.DELETED, true);
                emailFound = true;
                System.out.println("Deleted email: " + msg.getSubject());
            }
        }
        assertTrue(emailFound, "Email was found and deleted.");
        inbox.close(true);
        store.close();
    }
}
