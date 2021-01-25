package com.email.springboot.client;

import com.email.springboot.model.EmailDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public interface EmailClient {
   RestTemplate restTemplate = new RestTemplate();
   String BASE_URL="https://bw-interviews.herokuapp.com";

   default HttpHeaders getHeaders(String apiKey) {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.set("X-Api-Key", apiKey);
      return headers;
   }
   /**
    *  Send an email
    *
    * @param email email object
    *
    * @return response from client
    */
   <T> T sendEmail(EmailDto email);
}
