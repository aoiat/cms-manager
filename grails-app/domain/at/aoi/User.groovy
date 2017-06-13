package at.aoi

abstract class User<P extends ContestPlatform> {
    static belongsTo = [person: Person, contestPlatform: P]
    String username
    String password

    static constraints = {
        username nullable: false, blank: false
        password nullable: false
    }
}
