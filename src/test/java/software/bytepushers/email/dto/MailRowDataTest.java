package software.bytepushers.email.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MailRowDataTest {

    @Test
    void testMailRowDataGettersAndSetters(){
        String expectedName = "John";
        String expectedEmail = "john@gmail.com";

        String actualName = "John";
        String actualEmail = "john@gmail.com";

        MailRowData mailRowData = new MailRowData();
        mailRowData.setName(expectedName);
        mailRowData.setEmail(expectedEmail);

        assertEquals(expectedName, mailRowData.getName());
        assertEquals(expectedEmail, mailRowData.getEmail());
    }
}
