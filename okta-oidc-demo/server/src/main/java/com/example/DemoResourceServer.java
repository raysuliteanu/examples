package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DemoResourceServer {
    public static void main(String[] args) {
        SpringApplication.run(DemoResourceServer.class, args);
    }

    @GetMapping("/api")
    String api() {
        return "Made it to protected api on resource server!";
    }
}
