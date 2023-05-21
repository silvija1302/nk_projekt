package hr.project.model

data class Team(
    var id: Int? = null,
    var name: String,
    var shortName: String,
    var stadium: Stadium,
    var coach: Coach,
    var league: League
) {
    fun toDto() =
        TeamDto(
            this.id,
            this.name,
            this.shortName,
            this.stadium,
            this.coach,
            this.league,
            emptyList()
        )
}

data class TeamDto(
    var id: Int? = null,
    var name: String,
    var shortName: String,
    var stadium: Stadium,
    var coach: Coach,
    var league: League,
    var players: List<Player>
)