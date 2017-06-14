package at.aoi

import at.aoi.dto.UserDto

class UserController {
    static responseFormats = ['json', 'xml']

    def userService


    private def renderUserDto(UserDto dto) {
        respond([username    : dto.username,
                 password    : dto.password,
                 platform_url: dto.contestPlatformUrl,
                 contest_url : dto.contestUrl])
    }

    def createUserForContest(long contestId, String userEmail) {
        renderUserDto(userService.createUserForContest(contestId, userEmail))
    }

    def findUserForContest(long contestId, String userEmail) {
        renderUserDto(userService.findUserForContest(contestId, userEmail))
    }

}
