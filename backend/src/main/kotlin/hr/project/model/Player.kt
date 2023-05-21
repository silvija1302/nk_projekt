package hr.project.model

import io.micronaut.data.annotation.Id
import java.util.*

data class Player(
    @Id var id: Int,
    var firstName: String,
    var lastName: String,
    var birthDate: Date,
    var birthCity: String,
    var nationality: Nationality,
    var team: Team,
    var position: Position
)