package hr.project.service.impl

import hr.project.model.Match
import hr.project.model.Team
import hr.project.repository.MatchRepository
import hr.project.service.BasicService
import hr.project.validator.MatchValidator
import jakarta.inject.Singleton
import javax.validation.ValidationException

@Singleton
class MatchServiceImpl(
    var matchRepository: MatchRepository,
    var matchValidator: MatchValidator
): BasicService<Match> {
    override suspend fun getAll(): List<Match> {
        return matchRepository.findAll()
    }

    override suspend fun getById(id: Int): Match {
        return matchRepository.findById(id)
    }

    override suspend fun create(t: Match): Match {
        var validation = matchValidator.validationCreateMatch(t)
        if (!validation.isValid) {
            throw ValidationException(validation.errors.joinToString(",") { it })
        }
        matchRepository.save(t)
        return t
    }

    override suspend fun delete(id: Int): Boolean {
        if (!matchRepository.existsById(id))
            throw NoSuchElementException("Team not found for given id.")
        matchRepository.deleteById(id)

        if (matchRepository.existsById(id))
            return false
        return true
    }

    override suspend fun update(t: Match): Match {
        var validation = matchValidator.validateMatch(t)
        if (!validation.isValid) {
            throw ValidationException(validation.errors.joinToString(",") { it })
        }
        matchRepository.update(t)
        return t
    }

    private fun getAllMatchesForTeam(team: Team): List<Match> {
        return matchRepository.findByHomeTeamOrAwayTeam(team)
    }

}