package at.aoi

import at.aoi.exceptions.AlreadyExistsError
import at.aoi.exceptions.NotFoundException
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(UserService)
@Mock([CMSContestPlatform, CMSContest, CMSUser, Contest, Person, ContestPlatform])
class UserServiceSpec extends Specification {
    CMSContestPlatform cmsContestPlatform
    CMSContest cmsContest
    CMSUser cmsUser

    Person person

    def setup() {
        person = new Person(email: "garyye@gmail.com").save()
        cmsContestPlatform = new CMSContestPlatform(name: "CMS", url: "fake:url://irre.levant")
        cmsContestPlatform.save()

        cmsContest = new CMSContest(name: 'Contest 42', cmsContestId: 42, description: 'Empty', contestPlatform: cmsContestPlatform)
        cmsContest.save()

        cmsUser = new CMSUser(cmsId: 3, username: 'aoi1', password: 'password', person: person, contestPlatform:
                cmsContestPlatform, contest: cmsContest)
        cmsUser.save()
    }

    void "test create user should throw error when already exists"() {
        when: "user already exists"
        service.createUserForContest(cmsContest.id, person.email)
        then:
        thrown AlreadyExistsError
    }

    void "test existing cms user should be found"() {
        when:
        def dto = service.findUserForContest(cmsContest.id, person.email)
        then:
        dto.username == cmsUser.username
        dto.password == cmsUser.password
        dto.contestPlatformUrl == cmsContestPlatform.url
    }

    void "test find should throw not found error when not existing cms user "() {
        when:
        service.findUserForContest(cmsContest.id, "not@existing.gg")
        then:
        thrown NotFoundException
    }

    void "test find should throw not found error when not existing cms contest "() {
        when:
        service.findUserForContest(cmsContest.id + 10, person.email)
        then:
        thrown NotFoundException
    }
}
