package at.aoi

import java.time.ZonedDateTime

class CMSContest extends Contest {
    long cmsContestId
    String description
    ZonedDateTime start
    ZonedDateTime stop

    static constraints = {
        cmsContestId nullable: false
        description nullable: true
        start nullable: true
        stop nullable: true
    }
}
