package com.example.compose_ui.ui.utils.validations

import com.example.compose_ui.R
import com.example.compose_ui.ui.data.generics.forms.FromValidationBase
import com.example.compose_ui.ui.data.vo.ValidationResult

class FieldValidation : FromValidationBase<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = R.string.fieldRequired
            )
        }

        return ValidationResult(
            isSuccess = false,
            errorMessage = null
        )
    }
}