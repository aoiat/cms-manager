package at.aoi

import at.aoi.dto.UserDto
import grails.converters.JSON

class UserController {
    static responseFormats = ['json', 'xml']

    def userService


    private static def renderUserDto(UserDto dto) {
        [username: dto.username,
         password: dto.password,
         platform: dto.contestPlatformUrl,
         contest : dto.contestUrl]
    }

    def createUserForContest(long contestId, String userEmail) {
        def dto = userService.createUserForContest(contestId, userEmail)
        render([username: dto.username,
                password: dto.password,
                platform: dto.contestPlatformUrl,
                contest : dto.contestUrl] as JSON)
    }

    def queryUserForContest(long contestId, String userEmail) {
        renderUserDto(userService.findUserForPlatform(contestId, userEmail))
    }

}
