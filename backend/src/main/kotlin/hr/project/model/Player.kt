package hr.project.model

import java.util.*

data class Player(
    var id: Int,
    var firstName: String,
    var lastName: String,
    var birthDate: Date,
    var birthCity: String,
    var nationality: Nationality,
    var team: Team,
    var position: Position
)