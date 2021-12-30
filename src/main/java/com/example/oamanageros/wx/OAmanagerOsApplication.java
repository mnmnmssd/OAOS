package com.example.oamanageros.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class OAmanagerOsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAmanagerOsApplication.class, args);
    }

}
