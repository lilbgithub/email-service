package com.email.service;

import com.email.model.EmailDto;
import com.email.client.SnailgunClient;
import com.email.client.SpendgridClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final SpendgridClient spendgridClient;
    private final SnailgunClient snailgunClient;

    @Value("${spendgrid.enabled}")
    private boolean spendgridEnabled;

    @Autowired
    public EmailService(SpendgridClient spendgridClient, SnailgunClient snailgunClient) {
        this.spendgridClient = spendgridClient;
        this.snailgunClient = snailgunClient;
    }

    /**
     * Send email using one of the service provider
     *
     * @param emailDto email object
     */
    public ResponseEntity sendEmail(EmailDto emailDto) {
        if(spendgridEnabled){
            return spendgridClient.sendEmail(emailDto);
        }
        return snailgunClient.sendEmail(emailDto);
    }
}
