package at.aoi

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Person)
class PersonSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test saving should work"() {
        when:
        Person p = new Person(email: "garyye97@gmail.com")
        p.save()

        then:
        Person.findAll() == [p]
    }
}
