package com.kylin.faas.javatmp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@ServletComponentScan
public class JavaTmpApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaTmpApplication.class, args);
    }

}
