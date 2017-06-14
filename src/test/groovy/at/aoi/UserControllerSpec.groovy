package at.aoi

import at.aoi.dto.UserDto
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UserController)
class UserControllerSpec extends Specification {
    final Long CONTEST_ID = 42L
    final String EMAIL = "hello@gary.ye"

    UserService userService = Mock(UserService)

    def setup() {
        controller.userService = userService
    }

    def cleanup() {
    }

    def testCreateContestForUserCorrectResponse() {
        when:
        UserDto userDto = new UserDto(username: "gary", password: "password",
                contestPlatformUrl: "www.codeforces.com", contestUrl: "www.codeforces.com/contest/42")
        userService.createUserForContest(CONTEST_ID, EMAIL) >> userDto
        controller.createUserForContest(CONTEST_ID, EMAIL)
        then:
        response.json.username == "gary"
        response.json.password == "password"
        response.json.contest_url == "www.codeforces.com/contest/42"
        response.json.platform_url == "www.codeforces.com"
    }

}
