package com.purihuaman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
@EntityScan("com.purihuaman.model")
@EnableJpaRepositories("com.purihuaman.repository")
public class LibraryRestApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibraryRestApiApplication.class, args);
    }
}
