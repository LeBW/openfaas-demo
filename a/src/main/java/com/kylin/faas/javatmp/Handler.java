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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Controller
public class Handler {

    private static final Logger log = LoggerFactory.getLogger(Handler.class);

    private final RestTemplate restTemplate;

    public Handler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    private final static String gatewayHost = "gateway.openfaas";

    @GetMapping("/**")
    public ResponseEntity<String> print(@RequestParam Map<String, String> reqParams) {
        if (!addSpanTag(reqParams)) {
            return ResponseEntity.badRequest().body("Request params parsed failed");
        }

        String next = reqParams.get("next");
        String url = "http://" + gatewayHost + ":8080/function/" + next;
        log.info("Get {}", url);
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            log.info("Response: {}", response);
            return ResponseEntity.ok("success");
        } catch (RestClientException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.toString());
        }
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
