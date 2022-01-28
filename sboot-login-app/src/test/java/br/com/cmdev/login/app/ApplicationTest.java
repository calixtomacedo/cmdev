package br.com.cmdev.login.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTest {

	@Test
	public void mainTest() {
		Application.main(new String[] {"Test"});
	}

}
