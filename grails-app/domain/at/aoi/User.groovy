package at.aoi

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class User {
    static belongsTo = [person: Person, contestPlatform: ContestPlatform]
    String username
    String password

    static constraints = {
        username nullable: false, blank: false
        password nullable: false
    }
}
