package at.aoi

import at.aoi.dto.UserDto

class UserController {
    static responseFormats = ['json', 'xml']

    def userService


    private static def renderUserDto(UserDto dto) {
    }

    def createUserForContest(long contestId, String userEmail) {
        def dto = userService.createUserForContest(contestId, userEmail)
        respond([username    : dto.username,
                 password    : dto.password,
                 platform_url: dto.contestPlatformUrl,
                 contest_url : dto.contestUrl])
    }

    def queryUserForContest(long contestId, String userEmail) {
        renderUserDto(userService.findUserForPlatform(contestId, userEmail))
    }

}
