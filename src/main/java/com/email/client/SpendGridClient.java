package com.email.client;

import com.email.model.EmailDto;
import com.email.model.SpendGridEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class SpendGridClient implements EmailClient {
    public static final String SPEND_GRID_CLIENT_URI = BASE_URL + "/spendgrid/send_email";
    public static final String SPEND_GRID_ERROR_MSG = "Unable to post email using Spendgrid provider";

    @Value("${spendgrid.apikey}")
    private String apiKey;
    private RestTemplate restTemplate;

    public SpendGridClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity sendEmail(EmailDto email) {
        SpendGridEmail spendgridEmail = email.toSpendgridEmail();
        HttpEntity<SpendGridEmail> request = new HttpEntity<>(spendgridEmail, getHeaders(apiKey));
        SpendGridEmail response;

        try {
            response = restTemplate.postForObject(SPEND_GRID_CLIENT_URI, request, SpendGridEmail.class);
        } catch (Exception e) {
            log.error("message=\"{}\" from=\"{}\" to=\"{}\" subject=\"{}\"",
                    SPEND_GRID_ERROR_MSG, email.getFrom(), email.getTo(), email.getSubject(), e);
            return getInternalServerError();
        }
        return ResponseEntity
                .ok()
                .body(response);
    }
}
