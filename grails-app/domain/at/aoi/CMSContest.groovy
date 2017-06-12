package at.aoi

import java.time.ZonedDateTime

class CMSContest extends Contest {
    long cmsContestId
    String name
    String description
    ZonedDateTime start
    ZonedDateTime stop

    static constraints = {
    }
}
