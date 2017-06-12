package at.aoi

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class ContestPlatform {
    String name
    String url

    static constraints = {
        name blank: false, nullable: false
    }

    User createUser(Person person) {
        throw new UnsupportedOperationException("Abstract contest platform does not support this method")
    }

    User findUser(Person person) {
        throw new UnsupportedOperationException("Abstract contest platform does not support this method")
    }

    User createUserForContest(Person person, Contest contest) {
        // Many platforms do not have an account for every contest so we just call createUser
        return createUser(person)
    }

    User findUserForContest(Person person, Contest contest) {
        // Many platforms do not have an account for every contest so we just call findUser
        return findUser(person)
    }

}
