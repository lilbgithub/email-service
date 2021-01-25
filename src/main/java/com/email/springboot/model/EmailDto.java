package com.email.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@AllArgsConstructor
@Data
public class EmailDto implements Serializable {
    @NotBlank(message = "to email address must not be null or whitespace")
    @Email(message = "to email address must be valid")
    private String to;
    @NotBlank(message = "from email address must not be null or whitespace")
    @Email(message = "from email address must be valid")
    private String from;
    @NotBlank(message = "to_name must not be null or whitespace")
    private String to_name;
    @NotBlank(message = "from_name must not be null or whitespace")
    private String from_name;
    @NotBlank(message = "subject must not be null or whitespace")
    private String subject;
    @NotBlank(message = "body must not be null or whitespace")
    private String body;

    /**
     * Convert email DTO to SpendgridEmail
     *
     * @return SpendgridEmail object
     */
    public SpendgridEmail toSpendgridEmail() {
        SpendgridEmail email = new SpendgridEmail();
        email.setSender(this.from_name + "<" + this.from + ">");
        email.setRecipient(this.to_name + "<" + this.to + ">");
        email.setSubject(this.subject);
        email.setBody(this.body);

        return email;
    }

    /**
     * Convert email DTO to SnailgunEmail
     *
     * @return SnailgunEmail object
     */
    public SnailgunEmail toSnailgunEmail() {
        SnailgunEmail email = new SnailgunEmail();
        email.setFrom_name(this.from_name);
        email.setFrom_email(this.from);
        email.setTo_name(this.to_name);
        email.setTo_email(this.to);
        email.setSubject(this.subject);
        email.setBody(this.body);

        return email;
    }
}
