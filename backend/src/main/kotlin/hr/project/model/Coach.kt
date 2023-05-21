package hr.project.model

import java.util.*

data class Coach(
    var id: Int? = null,
    var firstName: String,
    var lastName: String,
    var birthDate: Date,
    var birthCity: String,
    var nationality: Nationality,
    var team: Team? = null
)