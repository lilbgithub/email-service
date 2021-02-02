package exception

import com.email.exception.CommonExceptionHandler
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import spock.lang.Specification

class CommonExceptionHandlerTest extends Specification {
    CommonExceptionHandler handler = new CommonExceptionHandler()

    def 'catchAllException handles exceptions'() {
        given: 'an exception'
        def exception = new Exception("foo")

        when: 'the handler is invoked'
        ResponseEntity responseEntity = handler.catchAllExceptions(exception)

        then: 'the expected response is returned'
        responseEntity.statusCode == HttpStatus.INTERNAL_SERVER_ERROR
    }

    def 'handleValidationExceptions handles exceptions'() {
        given: 'an MethodArgumentNotValidException'
        MethodArgumentNotValidException exception = Mock(MethodArgumentNotValidException)
        BindingResult bindingResult = Mock(BindingResult)
        bindingResult.getAllErrors() >> Arrays.asList(new FieldError("someObject", "someFieldName", "should not be empty"))
        exception.getBindingResult() >> bindingResult

        when: 'the handler is invoked'
        ResponseEntity responseEntity = handler.handleValidationExceptions(exception)

        then: 'the expected response is returned'
        responseEntity.statusCode == HttpStatus.BAD_REQUEST
        responseEntity.body == ["someFieldName":"should not be empty"]
    }
}
