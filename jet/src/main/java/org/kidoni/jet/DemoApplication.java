package org.kidoni.jet;

import javax.annotation.PreDestroy;

import com.hazelcast.jet.Jet;
import com.hazelcast.jet.JetInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
    private static final Logger LOG = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public JetInstance jetInstance() {
        return Jet.newJetInstance();
    }

    @PreDestroy
    public void shutdown() {
        LOG.info("shutting down Jet");
        Jet.shutdownAll();
    }
}
