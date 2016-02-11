package com.fico.afm.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.intercept.RunAsImplAuthenticationProvider;
import org.springframework.security.access.intercept.RunAsManager;
import org.springframework.security.access.intercept.RunAsManagerImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class RunAsDemoApplication {

    private static final String RUN_AS_KEY = "run-as-key";

    public static void main(String[] args) {
        SpringApplication.run(RunAsDemoApplication.class, args);
    }

    @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true, proxyTargetClass = true)
    public static class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {
        @Override
        protected RunAsManager runAsManager() {
            final RunAsManagerImpl runAsManager = new RunAsManagerImpl();
            runAsManager.setKey(RUN_AS_KEY);

            return runAsManager;
        }
    }

    @EnableWebSecurity
    public static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http
                    .httpBasic().and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/something").hasRole("USER")
                    .antMatchers(HttpMethod.GET, "/api/accounts").hasRole("USER")
                    .antMatchers(HttpMethod.POST, "/api/accounts").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/api/accounts/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PATCH, "/api/accounts/**").hasRole("ADMIN").and()
                    .csrf().disable();
        }

        @Override
        public void configure(final WebSecurity web) throws Exception {
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser("user").password("password").roles("USER", "RUN_AS_ADMIN").and()
                    .withUser("admin").password("admin").roles("USER", "ADMIN");

            auth.authenticationProvider(runAsAuthenticationProvider());
        }

        @Bean
        public AuthenticationProvider runAsAuthenticationProvider() {
            final RunAsImplAuthenticationProvider runAsAuthenticationProvider = new RunAsImplAuthenticationProvider();
            runAsAuthenticationProvider.setKey(RUN_AS_KEY);

            return runAsAuthenticationProvider;
        }
    }
}
