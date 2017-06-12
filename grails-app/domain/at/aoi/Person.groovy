package at.aoi

class Person {
    String email

    static mapping = {
        email index: 'Email_Index'
    }
    static constraints = {
        email nullable: false, unique: true
    }
}
