package at.aoi

abstract class Contest {
    static belongsTo = [contestPlatform: ContestPlatform]
    String name

    static constraints = {
        name nullable: true
    }

    static mapping = {
        tablePerHierarchy false
    }
}
