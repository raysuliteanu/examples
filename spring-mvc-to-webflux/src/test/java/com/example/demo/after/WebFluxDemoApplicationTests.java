package com.example.demo.after;

import com.example.demo.AbstractDemoApplicationTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@ActiveProfiles("webflux")
@SpringBootTest
class WebFluxDemoApplicationTests extends AbstractDemoApplicationTests {

	@Autowired
	RouterFunction<ServerResponse> helloRouterFunction;

	@Override
	protected void doSetup() {
		client = WebTestClient.bindToRouterFunction(helloRouterFunction).build();
	}
}
