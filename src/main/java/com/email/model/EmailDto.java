package com.email.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailDto {
    @NotBlank(message = "to email address must not be null or whitespace")
    @Email(message = "to email address must be valid")
    private String to;
    @NotBlank(message = "from email address must not be null or whitespace")
    @Email(message = "from email address must be valid")
    private String from;
    @JsonProperty("to_name")
    @NotBlank(message = "to_name must not be null or whitespace")
    private String toName;
    @JsonProperty("from_name")
    @NotBlank(message = "from_name must not be null or whitespace")
    private String fromName;
    @NotBlank(message = "subject must not be null or whitespace")
    private String subject;
    @NotBlank(message = "body must not be null or whitespace")
    private String body;

    /**
     * Convert email DTO to SpendgridEmail
     *
     * @return SpendgridEmail object
     */
    public SpendGridEmail toSpendgridEmail() {
        SpendGridEmail email = new SpendGridEmail();
        email.setSender(this.fromName + "<" + this.from + ">");
        email.setRecipient(this.toName + "<" + this.to + ">");
        email.setSubject(this.subject);
        email.setBody(this.body);

        return email;
    }

    /**
     * Convert email DTO to SnailgunEmail
     *
     * @return SnailgunEmail object
     */
    public SnailGunEmail toSnailgunEmail() {
        SnailGunEmail email = new SnailGunEmail();
        email.setFromName(this.fromName);
        email.setFromEmail(this.from);
        email.setToName(this.toName);
        email.setToEmail(this.to);
        email.setSubject(this.subject);
        email.setBody(this.body);

        return email;
    }
}
