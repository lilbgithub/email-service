package model

import com.email.model.EmailDto
import com.email.model.SnailGunEmail
import com.email.model.SpendGridEmail
import spock.lang.Specification

class EmailDtoTest extends Specification{

    def 'toSpendgridEmail test'(){
        given:'email dto object'
        EmailDto emailDto = new EmailDto()

        emailDto.setToName("bob")
        emailDto.setTo("bob@gmail.com")
        emailDto.setFrom("john")
        emailDto.setFromName("john@gmail.com")
        emailDto.setSubject("hello there")
        emailDto.setBody("some body")

        when:'I call toSpendgridEmail'
        SpendGridEmail spendGridEmail = emailDto.toSpendgridEmail();

        then:'expected transformation'
        spendGridEmail.sender ==  emailDto.fromName +'<' +emailDto.from+'>'
        spendGridEmail.recipient ==  emailDto.toName +'<' +emailDto.to+'>'
        spendGridEmail.subject == emailDto.subject
        spendGridEmail.body == emailDto.body
    }

    def 'toSnailgunEmail test'(){
        given:'email dto object'
        EmailDto emailDto = new EmailDto()

        emailDto.setToName("bob")
        emailDto.setTo("bob@gmail.com")
        emailDto.setFrom("john")
        emailDto.setFromName("john@gmail.com")
        emailDto.setSubject("hello there")
        emailDto.setBody("some body")

        when:'I call toSnailgunEmail'
        SnailGunEmail snailGunEmail = emailDto.toSnailgunEmail();

        then:'expected transformation'
        snailGunEmail.subject == emailDto.subject
        snailGunEmail.body == emailDto.body
        snailGunEmail.fromName == emailDto.fromName
        snailGunEmail.fromEmail == emailDto.from
        snailGunEmail.subject == emailDto.subject
        snailGunEmail.subject == emailDto.subject
    }
}
