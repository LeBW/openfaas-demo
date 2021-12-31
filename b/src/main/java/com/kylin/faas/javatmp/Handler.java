package com.kylin.faas.javatmp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Handler {
    private static final Logger log = LoggerFactory.getLogger(Handler.class);

    @GetMapping("/**")
    public ResponseEntity<String> print(@RequestParam Map<String, String> reqParams) {
        if (!addSpanTag(reqParams)) {
            return ResponseEntity.badRequest().body("Request params parsed failed");
        }
        log.info("Get invoked");
        return ResponseEntity.ok("b");
    }

    private boolean addSpanTag(Map<String, String> reqParams) {
        String json;
        try {
            json = new ObjectMapper().writeValueAsString(reqParams);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
        ActiveSpan.tag("reqParams", json);
        return true;
    }
}
