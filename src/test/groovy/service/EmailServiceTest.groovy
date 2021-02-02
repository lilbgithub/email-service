package service

import com.email.client.SnailGunClient
import com.email.client.SpendGridClient
import com.email.model.EmailDto
import com.email.service.EmailService
import org.jsoup.Jsoup
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class EmailServiceTest extends Specification {
    SpendGridClient spendGridClient = Mock(SpendGridClient)
    SnailGunClient snailGunClient = Mock(SnailGunClient)

    def 'sendEmail using spendGridClient'() {
        given: 'spendGridClient bean is injected'
        EmailDto emailDto = new EmailDto();
        emailDto.setBody("<b>hello")
        EmailService emailService = new EmailService(spendGridClient)

        and:'mock client'
        def body = "hello"
        1 * spendGridClient.sendEmail(_ as EmailDto) >> ResponseEntity.ok().body(body)

        when: "email service is called"
        ResponseEntity responseEntity = emailService.sendEmail(emailDto)

        then: 'expected response'
        responseEntity.statusCode == HttpStatus.OK
        responseEntity.body == body
    }

    def 'sendEmail using snailGunClient'() {
        given: 'spendGridEnabled is injected'
        EmailDto emailDto = new EmailDto()
        emailDto.setBody("<b>hello")
        EmailService emailService = new EmailService(snailGunClient)

        and:'mock client'
        def body = "hello"
        1 * snailGunClient.sendEmail(emailDto) >> ResponseEntity.ok().body(body)

        when: "email service is called"
        ResponseEntity responseEntity = emailService.sendEmail(emailDto)

        then: 'expected response'
        responseEntity.statusCode == HttpStatus.OK
        responseEntity.body == body
    }

}
