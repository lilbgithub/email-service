package service

import com.email.client.SnailGunClient
import com.email.client.SpendGridClient
import com.email.model.EmailDto
import com.email.service.EmailService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class EmailServiceTest extends Specification {
    SpendGridClient spendGridClient = Mock(SpendGridClient)
    SnailGunClient snailGunClient = Mock(SnailGunClient)
    EmailService emailService = new EmailService(spendGridClient, snailGunClient)

    def 'sendEmail uses spendGridClient when spendGridEnabled flag is true'() {
        given: 'spendGridEnabled flag is true'
        EmailDto emailDto = new EmailDto();
        emailService.spendGridEnabled = true

        when: "email service is called"
        def body = "some body"
        1 * spendGridClient.sendEmail(emailDto) >> ResponseEntity.ok().body(body)
        ResponseEntity responseEntity = emailService.sendEmail(emailDto)

        then: 'expected response'
        responseEntity.statusCode == HttpStatus.OK
        responseEntity.body == body
    }

    def 'sendEmail uses snailGunClient when spendGridEnabled flag is false'() {
        given: 'spendGridEnabled flag is true'
        EmailDto emailDto = new EmailDto();
        emailService.spendGridEnabled = false

        when: "email service is called"
        def body = "some body"
        1 * snailGunClient.sendEmail(emailDto) >> ResponseEntity.ok().body(body)
        ResponseEntity responseEntity = emailService.sendEmail(emailDto)

        then: 'expected response'
        responseEntity.statusCode == HttpStatus.OK
        responseEntity.body == body
    }

    def 'sendEmail by default uses snailGunClient when no spendGridEnabled flag value'() {
        given: 'spendGridEnabled flag is true'
        EmailDto emailDto = new EmailDto();
        emailService.spendGridEnabled = ""

        when: "email service is called"
        def body = "some body"
        1 * snailGunClient.sendEmail(emailDto) >> ResponseEntity.ok().body(body)
        ResponseEntity responseEntity = emailService.sendEmail(emailDto)

        then: 'expected response'
        responseEntity.statusCode == HttpStatus.OK
        responseEntity.body == body
    }

}
