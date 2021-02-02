package com.email.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SnailGunEmail {
    private String id;
    @JsonProperty("from_email")
    private String fromEmail;
    @JsonProperty("from_name")
    private String fromName;
    @JsonProperty("to_email")
    private String toEmail;
    @JsonProperty("to_name")
    private String toName;
    private String subject;
    private String body;
    private String status;
    private String created_at;
}
