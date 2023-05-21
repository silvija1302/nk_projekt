package hr.project.validator

import hr.project.model.Player
import hr.project.validator.model.ValidationResult
import jakarta.inject.Singleton

@Singleton
class PlayerValidator {

    fun validatePlayer(player: Player): ValidationResult {
        var isValid = ValidationResult()
        if (player.id == null) {
            isValid.isValid = false
            isValid.errors.plus("Player Id cannot be null")
        }
        if (player.firstName.isNullOrBlank() || player.firstName.count() < 3 || player.lastName.isNullOrBlank() || player.lastName.count() < 3) {
            isValid.isValid = false
            isValid.errors.plus("Player first and last name needs to be at least 3 characters")
        }
        if (player.nationality == null) {
            isValid.isValid = false
            isValid.errors.plus("Player nationality cannot be null")
        }
        return isValid
    }
}

