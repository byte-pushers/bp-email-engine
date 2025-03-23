package software.bytepushers.email;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class BpEmailAppApplicationTests {
	//TODO:
	@Test
	void contextLoads() {
		assertDoesNotThrow(() -> BpEmailAppApplication.main(new String[]{}));
	}

}
