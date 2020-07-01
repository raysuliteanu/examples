package com.example.demo.after;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Profile("webflux")
public class HelloHandler {
    private static final Logger LOG = LoggerFactory.getLogger(HelloHandler.class);

    public Mono<ServerResponse> hello(ServerRequest request) {
        final String user = request.pathVariable("user");
        LOG.info("handling /hello/" + user);

        return ServerResponse
                .ok()
                .bodyValue("hello, " + user);
    }
}
