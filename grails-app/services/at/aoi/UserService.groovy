package at.aoi

import at.aoi.dto.UserDto
import at.aoi.exceptions.NotFoundException
import grails.transaction.Transactional

@Transactional
class UserService {
    // TODO: Make sure that every method is really transactional because saving and then something fails -> RIP
    UserDto createUserForContest(long contestId, String userEmail) {
        Contest contest = Contest.findById(contestId)
        if (contest == null) {
            throw new NotFoundException("Contest with id ${contestId} not found")
        }
        Person person = Person.findOrCreateByEmail(userEmail)
        User user = contest.contestPlatform.createUserForContest(person, contest)
        return new UserDto(username: user.username, password: user.password,
                contestPlatformUrl: contest.contestPlatform.url)
    }

    UserDto findUserForContest(long contestId, String userEmail) {
        Contest contest = Contest.findById(contestId)
        if (contest == null) {
            throw new NotFoundException("Contest with id ${contestId} not found")
        }
        Person person = Person.findByEmail(userEmail)
        if (person == null) {
            throw new NotFoundException("Person with id ${userEmail} not found")
        }

        ContestPlatform contestPlatform = ContestPlatform.findById(contestPlatformId)
        return contestPlatform.findUser(person)
    }

    UserDto createUserForPlatform(long contestPlatformId, String userEmail) {
        ContestPlatform contestPlatform = ContestPlatform.findById(contestPlatformId)
        if (contestPlatform == null) {
            throw new NotFoundException("ContestPlatform with id ${contestPlatformId} not found")
        }
        Person person = Person.findOrCreateByEmail(userEmail)
        User user = contestPlatform.createUser(person)

        return new UserDto(username: user.username, password: user.password, contestPlatformUrl: contestPlatform.url)
    }

    UserDto findUserForPlatform(long contestPlatformId, String userEmail) {
        Person person = Person.findByEmail(userEmail)
        if (person == null) {
            throw new NotFoundException("Person with id ${userEmail} not found")
        }

        ContestPlatform contestPlatform = ContestPlatform.findById(contestPlatformId)
        return contestPlatform.findUser(person)
    }

}
