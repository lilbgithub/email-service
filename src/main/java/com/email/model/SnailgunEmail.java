package com.email.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SnailgunEmail {
    private String id;
    private String from_email;
    private String from_name;
    private String to_email;
    private String to_name;
    private String subject;
    private String body;
    private String status;
    private String created_at;
}
