package at.aoi

import at.aoi.cms.v12.CMSClient
import at.aoi.cms.v12.CMSContestDto
import at.aoi.cms.v12.CMSUserDto
import org.springframework.transaction.annotation.Transactional

/**
 * The CMS v1.2 an older version of https://github.com/cms-dev/cms
 *
 * It has a user account for every contest that it has on its database. In comparison, v1.3 has only one account for
 * every user on one platform.
 *
 * @author Gary Ye
 */
class CMSContestPlatform extends ContestPlatform<CMSUser> {
    static String cmsVersion = 'v1.2'
    // TODO: Maybe don't use admin url as contest platform url

    @Override
    CMSUser createUser(Person person) {
        throw new UnsupportedOperationException("Not supported in CMS ${cmsVersion}. Refer to v1.3+")
    }

    @Override
    CMSUser findUser(Person person) {
        throw new UnsupportedOperationException("Not supported in CMS ${cmsVersion}. Refer to v1.3+")
    }

    def generator = { String alphabet, int n ->
        new Random().with {
            (1..n).collect { alphabet[nextInt(alphabet.length())] }.join()
        }
    }

    @Override
    @Transactional
    CMSUser createUserForContest(Person person, Contest contest) {
        CMSContest cmsContest = CMSContest.findById(contest.id)
        Long cmsAccountId = CMSUser.count() + 1
        CMSUser cmsUser = new CMSUser(person: person,
                contest: cmsContest,
                contestPlatform: this,
                username: "aoi${cmsAccountId}",
                password: generator((('A'..'Z') + ('0'..'9')).join(), 6),
                cmsId: cmsAccountId)
        cmsUser.save()

        CMSClient cms = new CMSClient(this.url)
        CMSUserDto userDto = new CMSUserDto(username: cmsUser.username, password: cmsUser.password)
        CMSContestDto contestDto = new CMSContestDto(contestId: cmsContest.cmsContestId)
        cms.createUser(userDto, contestDto)
        return cmsUser
    }

    @Override
    CMSUser findUserForContest(Person person, Contest contest) {
        return CMSUser.findByPersonAndContest(person, CMSContest.findById(contest.id))
    }
}
