package com.email.controller;

import com.email.model.EmailDto;
import com.email.service.EmailService;
import org.jsoup.Jsoup;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * Request body Exception handler
     *
     * @param ex exception
     * @return Response entity
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
