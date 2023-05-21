package hr.project.validator

import hr.project.model.Match
import hr.project.validator.model.ValidationResult
import jakarta.inject.Singleton
import java.time.Instant
import java.util.*

@Singleton
class MatchValidator {

    fun validationCreateMatch(match: Match): ValidationResult {
        var isValid = validateMatch(match)
        if(match.date.before(Date.from(Instant.now()))) {
            isValid.isValid = false
            isValid.errors.plus("Date cannot be before current time.")
        }
        return isValid
    }

    fun validateMatch(match: Match): ValidationResult {
        var isValid = ValidationResult()
        if(match.id == null) {
            isValid.isValid = false
            isValid.errors.plus("Match id cannot be null")
        }
        if(match.homeTeam == null || match.awayTeam  == null) {
            isValid.isValid = false
            isValid.errors.plus("Match needs home and away team.")
        }
        if(match.date == null) {
            isValid.isValid = false
            isValid.errors.plus("Match needs a proper date.")
        }
        return isValid;
    }
}