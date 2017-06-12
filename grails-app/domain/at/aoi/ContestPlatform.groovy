package at.aoi

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class ContestPlatform<T extends Contest, S extends User> {
    String name
    String url

    static constraints = {
        name blank: false, nullable: false
    }

    S createUser(Person person) {
        throw new UnsupportedOperationException("Abstract contest platform does not support this method")
    }

    S findUser(Person person) {
        throw new UnsupportedOperationException("Abstract contest platform does not support this method")
    }

    S createUserForContest(Person person, T contest) {
        // Many platforms do not have an account for every contest so we just call createUser
        return createUser(person)
    }

    S findUserForContest(Person person, T contest) {
        // Many platforms do not have an account for every contest so we just call findUser
        return findUser(person)
    }

}
