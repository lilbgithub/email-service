package com.email.springboot.client;

import com.email.springboot.model.EmailDto;
import com.email.springboot.model.SpendgridEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

@Configuration
@Primary
@Slf4j
public class SpendgridClient implements EmailClient {
    private String clientUrl = BASE_URL + "/spendgrid/send_email";

    @Value("${spendgrid.apikey}")
    String apiKey;

    @Override
    public ResponseEntity sendEmail(EmailDto email) {
        SpendgridEmail spendgridEmail = email.toSpendgridEmail();
        HttpEntity<SpendgridEmail> request = new HttpEntity<>(spendgridEmail, getHeaders(apiKey));
        SpendgridEmail response;
        try {
            response = restTemplate.postForObject(clientUrl, request, SpendgridEmail.class);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity
                    .status(500)
                    .body("Internal server error");
        }
        return ResponseEntity
                .ok()
                .body(response);
    }
}
