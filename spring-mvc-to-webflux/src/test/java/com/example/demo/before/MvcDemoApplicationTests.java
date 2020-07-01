package com.example.demo.before;

import com.example.demo.AbstractDemoApplicationTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@ActiveProfiles("webmvc")
@SpringBootTest
class MvcDemoApplicationTests extends AbstractDemoApplicationTests {

	@Autowired
	HelloController helloController;

	@Override
	protected void doSetup() {
		client = WebTestClient.bindToController(helloController).build();
	}
}
