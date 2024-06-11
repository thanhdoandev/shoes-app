package com.example.compose_ui.ui.utils.validations

import com.example.compose_ui.R
import com.example.compose_ui.ui.cores.data.generics.forms.FromValidationBase
import com.example.compose_ui.ui.cores.data.vo.ValidationResult
import com.example.compose_ui.ui.extensions.isPasswordValid

class PasswordValidation : FromValidationBase<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = R.string.loginPasswordRequired,
            )
        }

        if (input.length < 8) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = R.string.loginLessPassword,
            )
        }

        if (!input.isPasswordValid()) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = R.string.loginPasswordInvalid,
            )
        }

        return ValidationResult(
            isSuccess = true,
            errorMessage = null
        )
    }
}