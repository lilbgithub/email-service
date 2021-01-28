package com.email.service;

import com.email.model.EmailDto;
import com.email.client.SnailGunClient;
import com.email.client.SpendGridClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final SpendGridClient spendGridClient;
    private final SnailGunClient snailGunClient;

    @Value("${spendgrid.enabled}")
    private boolean spendGridEnabled;

    @Autowired
    public EmailService(SpendGridClient spendGridClient, SnailGunClient snailGunClient) {
        this.spendGridClient = spendGridClient;
        this.snailGunClient = snailGunClient;
    }

    /**
     * Send email using one of the service provider
     *
     * @param emailDto email object
     */
    public ResponseEntity sendEmail(EmailDto emailDto) {
        if(spendGridEnabled){
            return spendGridClient.sendEmail(emailDto);
        }
        return snailGunClient.sendEmail(emailDto);
    }
}
