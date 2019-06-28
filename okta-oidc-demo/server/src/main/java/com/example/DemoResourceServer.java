package com.example;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DemoResourceServer {
    public static void main(String[] args) {
        SpringApplication.run(DemoResourceServer.class, args);
    }

    @GetMapping("/api")
    String api(Authentication authentication) {
        Assert.notNull(authentication, "auth is null");
        Assert.isInstanceOf(JwtAuthenticationToken.class, authentication, "not a JWT token");

        final Map<String, Object> tokenAttributes = ((JwtAuthenticationToken) authentication).getTokenAttributes();

        return String.format("Made it to protected api on resource server! (%s)", tokenAttributes.getOrDefault("tenantId", "invalid"));
    }

    @Configuration
    public static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            // @formatter:off
            http
                .authorizeRequests()
                    .antMatchers("/**").authenticated()
                    .and()
                .sessionManagement()
                    .disable()
                .httpBasic()
                    .disable()
                .oauth2ResourceServer()
                    .jwt();
            // @formatter:on
        }
    }

}
