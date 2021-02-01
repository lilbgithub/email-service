package com.email.controller;

import com.email.model.EmailDto;
import com.email.service.EmailService;
import org.jsoup.Jsoup;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(EmailController.BASE)
@Validated
public class EmailController {
    public static final String BASE = "/emails";
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * Send email controller
     *
     * @param email email dto
     * @return Response entity
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sendEmail(@Valid @RequestBody EmailDto email) {
        String emailBody = extractEmailBody(email.getBody());
        email.setBody(emailBody);

        return emailService.sendEmail(email);
    }

    private String extractEmailBody(String body) {
        return Jsoup.parse(body).wholeText();
    }

}
