package de.lmis.vhv.simplerest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
//@EnableWebSecurity(debug=true)
@EnableAsync
public class SimpleRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleRestApplication.class, args);
    }

}
