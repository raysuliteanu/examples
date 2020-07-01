package com.example.demo;

import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractDemoApplicationTests {
	protected WebTestClient client;

	protected abstract void doSetup();

	@BeforeEach
	public void setup() {
		doSetup();
	}

	@ParameterizedTest
	@ValueSource(strings = {"Ray", "Tom", "Steve"})
	public void hello(String argument) {
		client.get()
				.uri("/hello/{user}", argument)
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class)
				.consumeWith((Consumer<EntityExchangeResult<String>>) stringEntityExchangeResult -> {
					final String responseBody = stringEntityExchangeResult.getResponseBody();
					assertThat(responseBody).isEqualTo("hello, " + argument);
				});
	}
}
