package hr.project.validator

import hr.project.model.Team
import hr.project.validator.model.ValidationResult
import jakarta.inject.Singleton

@Singleton
class TeamValidator {

    fun validateTeam(team: Team): ValidationResult {
        var isValid = ValidationResult()
        if (team.id == null) {
            isValid.isValid = false
            isValid.errors.plus("Team Id cannot be null")
        }
        if (team.name.isNullOrBlank() || team.name.count() < 3) {
            isValid.isValid = false
            isValid.errors.plus("Team name needs to be at least 3 characters")
        }
        if(team.shortName.isNullOrBlank() || team.shortName.count() > 3) {
            isValid.isValid = false
            isValid.errors.plus("Team short name must not be blank and needs to be at least 3 characters.")
        }
        if (team.stadium == null) {
            isValid.isValid = false
            isValid.errors.plus("Team stadium cannot be null")
        }
        return isValid
    }
}