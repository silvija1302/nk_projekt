package hr.project.model

import java.util.*

data class Training(
    var id: Int,
    var team: Team,
    var coach: Coach,
    var dateTime: Date
)