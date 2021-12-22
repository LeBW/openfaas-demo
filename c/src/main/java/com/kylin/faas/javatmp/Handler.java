package com.kylin.faas.javatmp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Handler {
    private static final Logger log = LoggerFactory.getLogger(Handler.class);

    @GetMapping("/**")
    public ResponseEntity<String> print() {
        log.info("Get invoked");
        return ResponseEntity.ok("c");
    }
}
