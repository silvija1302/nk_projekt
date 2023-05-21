package hr.project.repository

import hr.project.model.Team
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface TeamRepository: CrudRepository<Team, Long> {
    override fun findAll(): List<Team>
    fun findById(id: Int): Team
    fun save(team: Team): Team
    fun update(team: Team): Team
    fun deleteById(id: Int)
    fun existsById(id: Int): Boolean
}