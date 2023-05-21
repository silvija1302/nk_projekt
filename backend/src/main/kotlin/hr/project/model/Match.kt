package hr.project.model

import java.util.*

data class Match(
    var id: Int,
    var homeTeam: Team,
    var awayTeam: Team,
    var date: Date
)