package hr.project.service.impl

import hr.project.model.Team
import hr.project.repository.TeamRepository
import hr.project.service.BasicService
import hr.project.validator.TeamValidator
import jakarta.inject.Singleton
import javax.validation.ValidationException

@Singleton
class TeamServiceImpl(
    var teamRepository: TeamRepository,
    var teamValidator: TeamValidator
): BasicService<Team> {
    override suspend fun getAll(): List<Team> {
        return teamRepository.findAll()
    }

    override suspend fun getById(id: Int): Team {
        return teamRepository.findById(id)
    }

    override suspend fun create(t: Team): Team {
        var validation = teamValidator.validateTeam(t)
        if (!validation.isValid) {
            throw ValidationException(validation.errors.joinToString(",") { it })
        }
        teamRepository.save(t)
        return t
    }

    override suspend fun delete(id: Int): Boolean {
        if (!teamRepository.existsById(id))
            throw NoSuchElementException("Team not found for given id.")
        teamRepository.deleteById(id)

        if (teamRepository.existsById(id))
            return false
        return true
    }

    override suspend fun update(t: Team): Team {
        var validation = teamValidator.validateTeam(t)
        if (!validation.isValid) {
            throw ValidationException(validation.errors.joinToString(",") { it })
        }
        teamRepository.update(t)
        return t
    }
}