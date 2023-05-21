package hr.project.model

import java.util.*
import io.micronaut.data.annotation.Id;


data class Training(
    @Id var id: Int,
    var team: Team,
    var coach: Coach,
    var dateTime: Date
)