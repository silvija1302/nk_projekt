package hr.project.validator.model

data class ValidationResult(
    var isValid: Boolean = true,
    var errors: List<String> = emptyList()
)