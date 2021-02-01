package com.email.service;

import com.email.client.EmailClient;
import com.email.model.EmailDto;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final EmailClient emailClient;

    @Autowired
    public EmailService(EmailClient emailClient) {
        this.emailClient = emailClient;
    }

    /**
     * Send email using one of the service provider
     *
     * @param emailDto email object
     */
    public ResponseEntity sendEmail(EmailDto emailDto) {
        String emailBody = extractEmailBody(emailDto.getBody());
        emailDto.setBody(emailBody);
        return emailClient.sendEmail(emailDto);
    }

    private String extractEmailBody(String body) {
        return Jsoup.parse(body).wholeText();
    }

}
