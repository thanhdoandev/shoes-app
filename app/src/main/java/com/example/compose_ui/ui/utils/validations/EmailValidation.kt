package com.example.compose_ui.ui.utils.validations

import com.example.compose_ui.R
import com.example.compose_ui.ui.data.generics.forms.FromValidationBase
import com.example.compose_ui.ui.data.vo.ValidationResult
import com.example.compose_ui.ui.extensions.isValidEmail
import com.example.compose_ui.ui.utils.UiText

class EmailValidation : FromValidationBase<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                isSuccess = false,
                errorMessage =R.string.loginEmailRequired
            )
        }

        if (!input.isValidEmail()) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = R.string.loginEmailInvalid
            )
        }

        return ValidationResult(
            isSuccess = true,
            errorMessage = null
        )
    }
}