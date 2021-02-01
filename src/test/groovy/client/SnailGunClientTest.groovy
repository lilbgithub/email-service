package client

import com.email.client.SnailGunClient
import com.email.model.EmailDto
import com.email.model.SnailGunEmail
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class SnailGunClientTest extends Specification {
    RestTemplate restTemplate = Mock(RestTemplate)
    SnailGunClient snailGunClient = new SnailGunClient(restTemplate)

    def 'sendEmail client success'() {
        given: 'Email dto object'
        EmailDto emailDto = new EmailDto()

        emailDto.setToName("bob")
        emailDto.setTo("bob@gmail.com")
        emailDto.setFrom("john")
        emailDto.setFromName("john@gmail.com")
        emailDto.setSubject("hello there")
        emailDto.setBody("some body")

        and: 'rest template post success'
        SnailGunEmail snailGunEmail = new SnailGunEmail()
        1 * restTemplate.postForObject(SnailGunClient.SNAIL_GUN_CLIENT_URI, _ as HttpEntity<SnailGunEmail>,
                SnailGunEmail.class) >> snailGunEmail

        when: 'sendEmail is called'
        ResponseEntity responseEntity = snailGunClient.sendEmail(emailDto)

        then: "success response"
        responseEntity.statusCode == HttpStatus.ACCEPTED
        responseEntity.body == snailGunEmail
    }

    def 'sendEmail client error scenario'() {
        given: 'Email dto object'
        EmailDto emailDto = new EmailDto()

        emailDto.setToName("bob")
        emailDto.setTo("bob@gmail.com")
        emailDto.setFrom("john")
        emailDto.setFromName("john@gmail.com")
        emailDto.setSubject("hello there")
        emailDto.setBody("some body")

        and: 'rest template post fails'
        1 * restTemplate.postForObject(SnailGunClient.SNAIL_GUN_CLIENT_URI, _ as HttpEntity<SnailGunEmail>,
                SnailGunEmail.class) >> { throw new RuntimeException() }

        when: 'sendEmail is called'
        ResponseEntity responseEntity = snailGunClient.sendEmail(emailDto)

        then: "success response"
        responseEntity.statusCode == HttpStatus.INTERNAL_SERVER_ERROR
        responseEntity.body == "Internal server error"
    }
}
