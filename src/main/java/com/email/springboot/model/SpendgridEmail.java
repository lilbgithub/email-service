package com.email.springboot.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SpendgridEmail {
    private String sender;
    private String recipient;
    private String subject;
    private String body;
}
