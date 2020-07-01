package com.example.demo.before;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Profile("webmvc")
@RestController
public class HelloController {
    private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);

    @GetMapping(path = "/hello/{user}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String hello(@PathVariable String user) {
        LOG.info("handling /hello/" + user);
        return "hello, " + user;
    }
}
