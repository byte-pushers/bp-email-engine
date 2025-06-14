package software.bytepushers.email.controller;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MailControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testUploadExcel_Success() {
        // Simulate Excel file upload
        MultipartFile file = new MockMultipartFile("file", "test.xlsx", "application/vnd.ms-excel", new byte[]{1, 2, 3});

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultipartFile> request = new HttpEntity<>(file, headers);
        String url = "http://localhost:" + port + "/email/uploadExcel";

        // Send the request
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Email sent successfully", response.getBody());
    }

}
