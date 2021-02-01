package com.email.controller;

import com.email.model.EmailDto;
import com.email.service.EmailService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class EmailController {
    public static final String BASE = "/emails";
    public static final String POST_EMAIL_INFO_MESSAGE = "Posting email";
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
        log.info("message=\"{}\" from=\"{}\" to=\"{}\" subject=\"{}\"",
                POST_EMAIL_INFO_MESSAGE, email.getFrom(), email.getTo(), email.getSubject());
        return emailService.sendEmail(email);
    }


}
