package at.aoi
/**
 * The CMS v1.2 an older version of https://github.com/cms-dev/cms
 *
 * It has a user account for every contest that it has on its database. In comparison, v1.3 has only one account for
 * every user on one platform.
 *
 * @author Gary Ye
 */
class CMSContestPlatform extends ContestPlatform {
    static String cmsVersion = 'v1.2'

    @Override
    User createUser(Person person) {
        throw new UnsupportedOperationException("Not supported in CMS v1.2. Refer to v1.3")
    }

    @Override
    User findUser(Person person) {
        throw new UnsupportedOperationException("Not supported in CMS v1.2. Refer to v1.3")
    }

    @Override
    User createUserForContest(Person person, Contest contest) {
        // API calls LUL
    }

    @Override
    User findUserForContest(Person person, Contest contest) {
        // API calls LUL
    }
}
