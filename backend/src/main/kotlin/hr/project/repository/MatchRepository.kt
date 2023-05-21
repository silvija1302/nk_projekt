package hr.project.repository

import hr.project.model.Match
import hr.project.model.Team
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface MatchRepository: CrudRepository<Match, Long> {
    override fun findAll(): List<Match>
    fun findById(id: Int): Match
    fun save(match: Match): Match
    fun update(match: Match): Match
    fun deleteById(id: Int)
    fun existsById(id: Int): Boolean
    fun findByHomeTeamOrAwayTeam(team: Team): List<Match>
}