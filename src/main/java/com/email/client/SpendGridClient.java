package com.email.client;

import com.email.model.EmailDto;
import com.email.model.SpendGridEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

@Configuration
@Primary
@Slf4j
public class SpendGridClient implements EmailClient {
    private final String SPEND_GRID_CLIENT_URI = BASE_URL + "/spendgrid/send_email";
    private final String SPEND_GRID_ERROR_MSG = "Unable to post email using Spendgrid provided";

    @Value("${spendgrid.apikey}")
    private String apiKey;

    @Override
    public ResponseEntity sendEmail(EmailDto email) {
        SpendGridEmail spendgridEmail = email.toSpendgridEmail();
        HttpEntity<SpendGridEmail> request = new HttpEntity<>(spendgridEmail, getHeaders(apiKey));
        SpendGridEmail response;
        try {
            response = restTemplate.postForObject(SPEND_GRID_CLIENT_URI, request, SpendGridEmail.class);
        } catch (Exception e) {
            log.info(SPEND_GRID_ERROR_MSG, e);
            return getInternalServerError();
        }
        return ResponseEntity
                .ok()
                .body(response);
    }
}
