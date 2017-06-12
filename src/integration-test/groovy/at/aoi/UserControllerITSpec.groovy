package at.aoi

import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
@Rollback
class UserControllerITSpec extends Specification {
    @Autowired
    UserController controller

    def setup() {
    }

    def cleanup() {
    }

    void "test query should respond with 404 when contest does not exist"() {
        when:
        Person p = new Person(email: "garyye97@gmail.com").save()
        controller.query(12345, p.email)
        then:
        controller.response.status == 404
    }
}
