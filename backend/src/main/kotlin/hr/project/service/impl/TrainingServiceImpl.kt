package hr.project.service.impl

import hr.project.model.Team
import hr.project.model.Training
import hr.project.repository.TrainingRepository
import hr.project.service.BasicService
import hr.project.validator.TrainingValidator
import javax.validation.ValidationException

class TrainingServiceImpl(
    var trainingRepository: TrainingRepository,
    var trainingValidator: TrainingValidator
): BasicService<Training> {
    override suspend fun getAll(): List<Training> {
        return trainingRepository.findAll()
    }

    override suspend fun getById(id: Int): Training {
        return trainingRepository.findById(id)
    }

    override suspend fun create(t: Training): Training {
        var validation = trainingValidator.validateTraining(t)
        if (!validation.isValid) {
            throw ValidationException(validation.errors.joinToString(",") { it })
        }
        trainingRepository.save(t)
        return t
    }

    override suspend fun delete(id: Int): Boolean {
        if (!trainingRepository.existsById(id))
            throw NoSuchElementException("Team not found for given id.")
        trainingRepository.deleteById(id)

        if (trainingRepository.existsById(id))
            return false
        return true    }

    override suspend fun update(t: Training): Training {
        var validation = trainingValidator.validateTraining(t)
        if (!validation.isValid) {
            throw ValidationException(validation.errors.joinToString(",") { it })
        }
        trainingRepository.update(t)
        return t    }

    private fun getAllTrainingForTeam(team: Team): List<Training> {
        return trainingRepository.findByTeam(team)
    }
}