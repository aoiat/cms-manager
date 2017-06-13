package at.aoi

import at.aoi.dto.UserDto
import at.aoi.exceptions.NotFoundException
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
@Rollback
class UserServiceIT extends Specification {
    @Autowired
    UserService userService

    CMSContestPlatform testContestPlatform

    void setup() {
        testContestPlatform = new CMSContestPlatform(name: "Integration test platform", url: "192.168.0.19:8889")
                .save(failOnError: true)
    }

    void "test create should throw not found exception when contest not found"() {
        when:
        Person p = new Person(email: "garyye97@gmail.com").save(failOnError: true)
        userService.createUserForContest(12345, p.email)
        then:
        thrown NotFoundException
    }

    void "test create works as intended"() {
        when:
        Person p = new Person(email: "garyye97@gmail.com").save()
        Contest testContest = new CMSContest(contestPlatform: testContestPlatform, cmsContestId: 3).save()
        UserDto dto = userService.createUserForContest(testContest.getId(), p.email)
        then:
        dto.username == 'aoi1'
        dto.password != null && dto.password.length() > 5
        dto.contestPlatformUrl == testContestPlatform.url
    }
}
