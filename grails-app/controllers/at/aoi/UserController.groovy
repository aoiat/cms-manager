package at.aoi

import at.aoi.dto.UserDto
import at.aoi.exceptions.NotFoundException

class UserController {
    static responseFormats = ['json', 'xml']

    def userService

    private def handleNotFoundException(NotFoundException e) {
        response.status = 404
        [error: "${e.message}"]
    }

    private static def renderUserDto(UserDto dto) {
        [username: dto.username,
         password: dto.password,
         platform: dto.contestPlatformUrl,
         contest : dto.contestUrl]
    }

    def createUserForContest(long contestId, String userEmail) {
        renderUserDto(userService.createUserForContest(contestId, userEmail))
    }

    def queryUserForContest(long contestId, String userEmail) {
        renderUserDto(userService.findUserForPlatform(contestId, userEmail))
    }

}
