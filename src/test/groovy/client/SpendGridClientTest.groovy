package client

import com.email.client.SpendGridClient
import com.email.model.EmailDto
import com.email.model.SnailGunEmail
import com.email.model.SpendGridEmail
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class SpendGridClientTest extends Specification {
    RestTemplate restTemplate = Mock(RestTemplate)
    SpendGridClient spendGridClient = new SpendGridClient(restTemplate)

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
        SpendGridEmail spendGridEmail = new SpendGridEmail()
        1 * restTemplate.postForObject(SpendGridClient.SPEND_GRID_CLIENT_URI, _ as HttpEntity<SpendGridEmail>,
                SpendGridEmail.class) >> spendGridEmail

        when: 'sendEmail is called'
        ResponseEntity responseEntity = spendGridClient.sendEmail(emailDto)

        then: "success response"
        responseEntity.statusCode == HttpStatus.OK
        responseEntity.body == spendGridEmail
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
        1 * restTemplate.postForObject(SpendGridClient.SPEND_GRID_CLIENT_URI, _ as HttpEntity<SpendGridEmail>,
                SpendGridEmail.class) >> { throw new RuntimeException() }

        when: 'sendEmail is called'
        ResponseEntity responseEntity = spendGridClient.sendEmail(emailDto)

        then: "success response"
        responseEntity.statusCode == HttpStatus.INTERNAL_SERVER_ERROR
        responseEntity.body == "Internal server error"
    }
}
