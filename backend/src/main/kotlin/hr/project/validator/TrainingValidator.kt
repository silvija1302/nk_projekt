package hr.project.validator

import hr.project.model.Training
import hr.project.validator.model.ValidationResult
import jakarta.inject.Singleton
import java.time.Instant
import java.util.*

@Singleton
class TrainingValidator {
    fun validateTraining(training: Training): ValidationResult {
        var isValid = ValidationResult()
        if(training.id == null) {
            isValid.isValid = false
            isValid.errors.plus("Training Id cannot be null")
        }
        if(training.coach == null) {
            isValid.isValid = false
            isValid.errors.plus("Coach needs to be added to training")
        }
        if(training.team == null) {
            isValid.isValid = false
            isValid.errors.plus("Team needs to be added to training")
        }
        if(training.dateTime == null || training.dateTime.before(Date.from(Instant.now()))) {
            isValid.isValid = false
            isValid.errors.plus("Training date must be after current time")
        }
        return isValid

    }
}