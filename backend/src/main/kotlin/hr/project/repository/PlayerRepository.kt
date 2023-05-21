package hr.project.repository

import hr.project.model.Player
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface PlayerRepository : CrudRepository<Player, Long> {
    override fun findAll(): List<Player>
    fun findById(id: Int): Player
    fun save(player: Player): Player
    fun update(player: Player): Player
    fun deleteById(id: Int)
    fun existsById(id: Int): Boolean
    fun findByTeamId(id: Int): List<Player>
}