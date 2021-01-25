package com.email.springboot.client;

import com.email.springboot.model.EmailDto;
import com.email.springboot.model.SnailgunEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

@Configuration
@Slf4j
public class SnailgunClient implements EmailClient {
    private String clientUrl = BASE_URL + "/snailgun/send_email";

    @Value("${snailgun.apiKey}")
    String apiKey;

    @Override
    public ResponseEntity sendEmail(EmailDto email) {
        SnailgunEmail snailgunEmail = email.toSnailgunEmail();
        log.info(snailgunEmail.toString());
        HttpEntity<SnailgunEmail> request = new HttpEntity<>(snailgunEmail, getHeaders(apiKey));
        SnailgunEmail response;
        try {
            response = restTemplate.postForObject(clientUrl, request, SnailgunEmail.class);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity
                    .status(500)
                    .body("Internal server error");
        }
        return ResponseEntity
                .accepted()
                .body(response);
    }
}
