package at.aoi

import at.aoi.dto.UserDto
import at.aoi.exceptions.NotFoundException
import grails.rest.RestfulController

class UserController extends RestfulController<User> {
    static responseFormats = ['json', 'xml']

    def userService

    UserController() {
        super(User)
    }

    private def handleNotFoundException(NotFoundException e) {
        response.status = 404
        [error: "${e.message}"]
    }

    private static def renderUserDto(UserDto dto) {
        [username: dto.username,
         password: dto.password,
         platform: dto.contestPlatformUrl]
    }

    def createUserForContest(int contestId, String userEmail) {
        UserDto dto = userService.createUserForContest(contestId, userEmail)
        renderUserDto(dto)
    }

    def queryUserForContest(int contestId, String userEmail) {
        renderUserDto(userService.findUserForPlatform(contestId, userEmail))
    }

}
