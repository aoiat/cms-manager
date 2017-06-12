package at.aoi

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Contest {
    static belongsTo = [contestPlatform: ContestPlatform]
    String name

    static constraints = {
        name blank: false
    }
}
