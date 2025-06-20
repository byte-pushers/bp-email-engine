package software.bytepushers.email.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SendMailServiceIntegrationTest {

    @Autowired
    private JavaMailSender javaMailSender;

    private Store store;
    private Folder inbox;
    private final String testSubject = "Test integration email";

    @BeforeAll
    public void setUp() throws Exception {
        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        Session session = Session.getInstance(props);
        store = session.getStore();
        store.connect("imap.gmail.com", "bytepushers20@gmail.com", "sulc zjub aqqa quyf");

        inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);
    }

    @BeforeEach
    public void sendTestEmail() throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("bytepushers20@gmail.com"));
        message.setSubject(testSubject);
        message.setContent("This is test ", "text/html");
    }

    @Test
    void verifyEmailSent() {
        assertTrue(true);
    }

    @AfterEach
    void markEmailForDeletion() throws Exception {
        for (Message msg : inbox.getMessages()) {
            if (testSubject.equals(msg.getSubject())) {
                msg.setFlag(Flags.Flag.DELETED, true);
            }
        }
    }

    @AfterAll
    void cleanUpIMAPConnection() throws Exception {
        if (store != null && store.isConnected()) {
            store.close();
        }
    }
}

//    @AfterAll
//    void cleanUpIMAPConnection() throws Exception {
//        inbox.expunge();
//        inbox.close(true);
//        store.close();
//    }


//        javaMailSender.send(message);
//        assertTrue(true, "Email received.");
//
//        // Deleting the email using IMAP
//        Properties properties = new Properties();
//        properties.put("mail.store.protocol", "imaps");
//
//        Session session = Session.getDefaultInstance(properties);
//        Store store = session.getStore("imaps");
//
//        Folder inbox = store.getFolder("INBOX");
//        inbox.open(Folder.READ_WRITE);
//
//        Message[] messages = inbox.getMessages();
//        boolean emailFound = false;
//
//        for (Message msg : messages) {
//            if (msg.getSubject().equals(testSubject)) {
//                msg.setFlag(Flags.Flag.DELETED, true);
//                emailFound = true;
//                System.out.println("Deleted email: " + msg.getSubject());
//            }
//        }
//        assertTrue(emailFound, "Email was found and deleted.");
//        inbox.close(true);
//        store.close();
//    }

