package config

import com.email.client.EmailClient
import com.email.client.SnailGunClient
import com.email.client.SpendGridClient
import com.email.config.Configuration
import spock.lang.Specification

class ConfigurationTest extends Specification{

    def 'application configures the SpendGridClient bean if spendGridEnabled is true '(){
        given:'application properties spendGridEnabled is true'
        Configuration configuration = new Configuration();

        configuration.spendGridEnabled = true

        when:'email client is called'
        EmailClient client = configuration.emailClient()

        then:'SpendGridClient bean is returned'
        client instanceof SpendGridClient
    }

    def 'application configures the SnailGunClient bean if spendGridEnabled is false '(){
        given:'application properties spendGridEnabled is true'
        Configuration configuration = new Configuration();

        configuration.spendGridEnabled = false

        when:'email client is called'
        EmailClient client = configuration.emailClient()

        then:'SpendGridClient bean is returned'
        client instanceof SnailGunClient
    }
}
