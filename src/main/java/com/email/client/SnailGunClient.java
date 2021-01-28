package com.email.client;

import com.email.model.EmailDto;
import com.email.model.SnailGunEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SnailGunClient implements EmailClient {
    private final String SNAIL_GUN_CLIENT_URI = BASE_URL + "/snailgun/send_email";
    private final String SNAIL_GUN__ERROR_MSG = "Unable to post email using SnailGun provided";

    @Value("${snailgun.apiKey}")
    private String apiKey;

    @Override
    public ResponseEntity sendEmail(EmailDto email) {
        SnailGunEmail snailgunEmail = email.toSnailgunEmail();
        HttpEntity<SnailGunEmail> request = new HttpEntity<>(snailgunEmail, getHeaders(apiKey));
        SnailGunEmail response;
        try {
            response = restTemplate.postForObject(SNAIL_GUN_CLIENT_URI, request, SnailGunEmail.class);
        } catch (Exception e) {
            log.error(SNAIL_GUN__ERROR_MSG, e);
            return getInternalServerError();
        }
        return ResponseEntity
                .accepted()
                .body(response);
    }
}
