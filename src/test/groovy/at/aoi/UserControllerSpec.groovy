package at.aoi

import at.aoi.dto.UserDto
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Unit testing the UserController
 */
@TestFor(UserController)
class UserControllerSpec extends Specification {
    final Long CONTEST_ID = 42L
    final String EMAIL = "hello@gary.ye"
    final UserDto USER_DTO = new UserDto(username: "gary", password: "password", contestPlatformUrl: "www.cf.com",
            contestUrl: "www.cf.com/contest/42")

    UserService userService

    def setup() {
        userService = Mock(UserService.class)
        controller.userService = userService
    }


    def testCreateUserForContestJSONResponse() {
        when:
        controller.createUserForContest(CONTEST_ID, EMAIL)

        then:
        1 * userService.createUserForContest(CONTEST_ID, EMAIL) >> USER_DTO
        response.json.username == USER_DTO.username
        response.json.password == USER_DTO.password
        response.json.contest_url == USER_DTO.contestUrl
        response.json.platform_url == USER_DTO.contestPlatformUrl
    }


    def testQueryUserFromPersonAndContestCorrectJSONResponse() {
        when:
        controller.findUserForContest(CONTEST_ID, EMAIL)

        then:
        1 * userService.findUserForContest(CONTEST_ID, EMAIL) >> USER_DTO
        response.json.username == USER_DTO.username
        response.json.password == USER_DTO.password
        response.json.contest_url == USER_DTO.contestUrl
        response.json.platform_url == USER_DTO.contestPlatformUrl
    }


}
