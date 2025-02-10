package software.bytepushers.email.controller;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MailControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers;
    private MultipartFile file;

    @BeforeAll
    void globalSetup() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        System.out.println("Starting MailControllerIT...");
    }

    @BeforeEach
    void prepareRequest() {
        file = new MockMultipartFile("file", "test.xlsx", "application/vnd.ms-excel", new byte[]{1, 2, 3});
    }

    @Test
    void testUploadExcel_Success() {
        String url = "http://localhost:" + port + "/uploadExcel";

        // Simulate a simple Excel-like file (CSV format as test input)
        byte[] fileContent = "name,email\nJohn Doe,bytepushers20@gmail.com".getBytes();

        // Create HttpEntity for the file part
        HttpHeaders fileHeaders = new HttpHeaders();
        fileHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        HttpEntity<byte[]> fileEntity = new HttpEntity<>(fileContent, fileHeaders);

        // Wrap it in a multipart request body
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileEntity); // Must match @RequestParam("file")

    }

    @AfterEach
    void afterEachRequest() {
        System.out.println("✅ MailController test completed.");
    }

    @AfterAll
    void cleanUp() {
        System.out.println("🧹 MailControllerIT teardown complete.");
    }

}
