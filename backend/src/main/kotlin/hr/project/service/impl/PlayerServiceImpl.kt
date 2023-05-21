package hr.project.service.impl

import hr.project.model.Player
import hr.project.repository.PlayerRepository
import hr.project.service.BasicService
import hr.project.validator.PlayerValidator
import jakarta.inject.Singleton
import javax.validation.ValidationException

@Singleton
class PlayerServiceImpl(
    var playerRepository: PlayerRepository,
    var playerValidator: PlayerValidator
): BasicService<Player> {

    override suspend fun getAll(): List<Player> {
        return playerRepository.findAll()
    }

    override suspend fun getById(id: Int): Player {
        return playerRepository.findById(id)
    }

    override suspend fun create(player: Player): Player {
        var validation = playerValidator.validatePlayer(player)
        if (!validation.isValid) {
            throw ValidationException(validation.errors.joinToString(",") { it })
        }
        playerRepository.save(player)
        return player
    }

    override suspend fun delete(id: Int): Boolean {
        if (!playerRepository.existsById(id))
            throw NoSuchElementException("User not found for given id.")
        playerRepository.deleteById(id)

        if (playerRepository.existsById(id))
            return false
        return true
    }

    override suspend fun update(player: Player): Player {
        var validation = playerValidator.validatePlayer(player)
        if (!validation.isValid) {
            throw ValidationException(validation.errors.joinToString(",") { it })
        }
        playerRepository.update(player)
        return player
    }

    suspend fun getPlayerFromTeam(teamId: Int):List<Player> {
        return playerRepository.findByTeamId(teamId)
    }
}