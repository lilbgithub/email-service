import com.email.controller.EmailController
import com.email.model.EmailDto
import com.email.service.EmailService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class EmailControllerTest extends Specification {

    EmailService emailService = Mock(EmailService)
    EmailController emailController = new EmailController(emailService)

    def 'sendEmail controller success'() {
        given: 'an email to be send'
        EmailDto emailDto = new EmailDto()

        emailDto.setToName("bob")
        emailDto.setTo("bob@gmail.com")
        emailDto.setFrom("john")
        emailDto.setFromName("john@gmail.com")
        emailDto.setSubject("hello there")
        emailDto.setBody("some body")

        and: 'mock service success'
        ResponseEntity serviceResponse = ResponseEntity
                .ok()
                .body("some response");
        1 * emailService.sendEmail(emailDto) >> serviceResponse

        when: 'I call sendEmail controller'
        ResponseEntity responseEntity = emailController.sendEmail(emailDto)

        then: "expect success response"
        responseEntity.statusCode == HttpStatus.OK
        responseEntity.body == serviceResponse.body

    }

    def 'sendEmail controller error response when service errors'() {
        given: 'an email request to be sent'
        EmailDto emailDto = new EmailDto()

        emailDto.setToName("bob")
        emailDto.setTo("bob@gmail.com")
        emailDto.setFrom("john")
        emailDto.setFromName("john@gmail.com")
        emailDto.setSubject("hello there")
        emailDto.setBody("some body")

        and: 'mock service errors'
        ResponseEntity serviceResponse = ResponseEntity
                .badRequest()
                .body("invalid request");
        1 * emailService.sendEmail(emailDto) >> serviceResponse

        when: 'I call sendEmail controller'
        ResponseEntity responseEntity = emailController.sendEmail(emailDto)

        then: "expect error response"
        responseEntity.statusCode == HttpStatus.BAD_REQUEST
        responseEntity.body == serviceResponse.body
    }

}
