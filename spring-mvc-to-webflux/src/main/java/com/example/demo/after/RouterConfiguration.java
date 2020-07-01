package com.example.demo.after;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Profile("webflux")
@EnableWebFlux
@Configuration
public class RouterConfiguration {
    @Bean
    public HelloHandler helloHandler() {
        return new HelloHandler();
    }

    @Bean
    public RouterFunction<ServerResponse> helloRouterFunction(HelloHandler helloHandler) {
        return RouterFunctions.route()
                .GET("/hello/{user}", helloHandler::hello)
                .build();
    }
}
