package at.aoi

import java.time.LocalDate

class Person {
    String firstName
    String lastName
    String email
    LocalDate birthDate

    static mapping = {
        email index: 'Email_Index'
    }
    static constraints = {
        email nullable: false, unique: true
    }
}
