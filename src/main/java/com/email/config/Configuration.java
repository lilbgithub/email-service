package com.email.config;

import com.email.client.EmailClient;
import com.email.client.SnailGunClient;
import com.email.client.SpendGridClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Value("${spendgrid.enabled}")
    private boolean spendGridEnabled;

    @Bean
    public EmailClient emailClient(){
        RestTemplate restTemplate = new RestTemplate();
        if(spendGridEnabled){
            return new SpendGridClient(restTemplate);
        }
        return new SnailGunClient(restTemplate);
    }
}
