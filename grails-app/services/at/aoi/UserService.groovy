package at.aoi

import at.aoi.dto.UserDto
import at.aoi.exceptions.AlreadyExistsError
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

        // Only CMS v1.2 has this feature
        if (CMSUser.findByPersonAndContest(person, CMSContest.findById(contest.id)) != null) {
            throw new AlreadyExistsError("CMS user already exists")
        }
        User user = contest.contestPlatform.createUserForContest(person, contest)
        return new UserDto(username: user.username, password: user.password,
                contestPlatformUrl: contest.contestPlatform.url)
    }

    /**
     * Currently there is only one type of contest that has user accounts
     * @param contestId the id of the contest
     * @param userEmail the email address of the user
     * @return a user dto with the user authentication details
     */
    UserDto findUserForContest(long contestId, String userEmail) {
        CMSContest contest = CMSContest.findById(contestId)
        if (contest == null) {
            throw new NotFoundException("CMS Contest with id ${contestId} not found")
        }
        Person person = Person.findByEmail(userEmail)
        if (person == null) {
            throw new NotFoundException("Person with id ${userEmail} not found")
        }

        CMSUser user = CMSUser.findByContestAndPerson(contest, person)
        if (user == null) {
            throw new NotFoundException("User not found for ${contest.id} and ${userEmail}")
        }
        return new UserDto(username: user.username, password: user.password,
                contestPlatformUrl: contest.contestPlatform.url)
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
