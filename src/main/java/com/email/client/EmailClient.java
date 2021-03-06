package com.email.client;

import com.email.model.EmailDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public interface EmailClient {
   String BASE_URL="https://bw-interviews.herokuapp.com";

   default HttpHeaders getHeaders(String apiKey) {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.set("X-Api-Key", apiKey);
      return headers;
   }

   default ResponseEntity getInternalServerError(){
      return ResponseEntity
              .status(500)
              .body("Internal server error");
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
