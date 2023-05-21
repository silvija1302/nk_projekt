package hr.project.repository

import hr.project.model.Team
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import jakarta.persistence.EntityManager
import java.util.*
import javax.transaction.Transactional

@Repository
@Transactional
interface TeamRepository : CrudRepository<Team, Int> {
    override fun findAll(): List<Team>
    override fun findById(id: Int): Optional<Team>
    override fun delete(entity: Team)
    override fun existsById(id: Int): Boolean
}