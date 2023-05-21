package hr.project.repository

import hr.project.model.Team
import hr.project.model.Training
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface TrainingRepository: CrudRepository<Training, Long> {
    override fun findAll(): List<Training>
    fun findById(id: Int): Training
    fun save(training: Training): Training
    fun update(training: Training): Training
    fun deleteById(id: Int)
    fun findByTeam(team: Team): List<Training>
    fun existsById(id: Int): Boolean
}