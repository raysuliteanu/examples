package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@SpringBootApplication
public class DemoClient {
    private static final Logger LOG = LoggerFactory.getLogger(DemoClient.class);

    @Value("#{ @environment['example.server'] }")
    private String remoteResourceServer;

    private WebClient webClient;

    public DemoClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoClient.class, args);
    }

    @GetMapping("/")
    String home(@AuthenticationPrincipal OidcUser user) {
        return "Hello " + user.getGivenName() + " from tenant " + user.getClaimAsString("tenantId");
    }

    @GetMapping("/api")
    String api() {
        LOG.debug("resource server url: {}", remoteResourceServer);
        return this.webClient
                .get()
                .uri(remoteResourceServer + "/api")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Configuration
    public static class OktaWebClientConfig {

        @Bean
        WebClient webClient(ClientRegistrationRepository clientRegistrations, OAuth2AuthorizedClientRepository authorizedClients) {
            ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
                    new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrations, authorizedClients);
            oauth2.setDefaultOAuth2AuthorizedClient(true);
            oauth2.setDefaultClientRegistrationId("okta");
            return WebClient.builder()
                    .apply(oauth2.oauth2Configuration())
                    .build();
        }
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
                    .oauth2Login();
            // @formatter:on
        }
    }
}
