package com.example.compose_ui.ui.screens.auth.register

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_ui.R
import com.example.compose_ui.ui.bases.ContainerPage
import com.example.compose_ui.ui.components.cores.JPButton
import com.example.compose_ui.ui.components.cores.JPCard
import com.example.compose_ui.ui.components.cores.JPColumn
import com.example.compose_ui.ui.components.cores.JPIcon
import com.example.compose_ui.ui.components.cores.JPInput
import com.example.compose_ui.ui.components.cores.JPRow
import com.example.compose_ui.ui.components.cores.JPSecondaryButton
import com.example.compose_ui.ui.components.cores.JPSpacer
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.components.cores.JPTextButton
import com.example.compose_ui.ui.cores.data.enums.EFieldType
import com.example.compose_ui.ui.cores.data.model.UiState
import com.example.compose_ui.ui.screens.iuUtils.UiStates
import com.example.compose_ui.ui.theme.primaryColor
import com.example.compose_ui.ui.theme.primaryText
import com.example.compose_ui.ui.theme.size_16
import com.example.compose_ui.ui.theme.size_28
import com.example.compose_ui.ui.theme.size_6

@Composable
fun Register(
    onBack: () -> Unit = {},
    onOpenHome: () -> Unit = {},
    viewModel: RegisterViewModels = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    viewModel.run {
        LaunchedEffect(uiRegisterState) {
            if (uiRegisterState.isRegisterSuccess) {
                onOpenHome()
            }
        }
        RegisterScreen(
            uiState = uiState,
            uiStateRegister = uiRegisterState,
            onBack = onBack,
            onUpdateData = { type, value ->
                onRegisterEvent(RegisterEvents.InputChanged(type, value))
            },
            onRegister = { onRegisterEvent(RegisterEvents.Submit) }
        )
    }
}

@Composable
private fun RegisterScreen(
    uiState: UiState,
    uiStateRegister: RegisterData,
    onBack: () -> Unit = {},
    onUpdateData: (field: EFieldType, value: String) -> Unit = { _, _ -> },
    onRegister: () -> Unit = {}
) {
    ContainerPage(uiState = uiState) {
        JPColumn {
            if (UiStates().isKeyBoardHide) {
                JPSpacer(h = size_28)
                JPIcon(
                    icon = Icons.Default.ArrowBackIosNew,
                    mStart = size_16,
                    onClick = { onBack() })
                JPSpacer(h = size_28)
            }
            JPCard(
                isMaxSize = true,
                roundTopStart = 28.dp,
                roundTopEnd = 28.dp,
                bgColor = primaryColor,
                contentColor = Color.White
            ) {
                JPText(
                    modifier = Modifier.fillMaxWidth(),
                    mTop = 40.dp,
                    text = stringResource(id = R.string.registerTitle),
                    isCenter = true,
                    style = MaterialTheme.typography.titleLarge
                )
                JPText(
                    mTop = 4.dp,
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.loginDesc),
                    isCenter = true
                )

                uiStateRegister.run {
                    JPInput(
                        value = fullName,
                        onValueChange = {
                            onUpdateData(EFieldType.YOUR_NAME, it)
                        },
                        label = stringResource(id = R.string.registerYourName),
                        mTop = 40.dp,
                        focusBorderColor = Color.White,
                        contentColor = Color.White,
                        unFocusLabelColor = primaryText,
                        isError = fullNameError != null,
                        errorMessage = fullNameError?.let {
                            stringResource(it, stringResource(id = R.string.registerFullName))
                        }
                    )
                    JPInput(
                        value = email,
                        contentColor = Color.White,
                        onValueChange = {
                            onUpdateData(EFieldType.EMAIL, it)
                        },
                        label = stringResource(id = R.string.emailLabel),
                        focusBorderColor = Color.White,
                        unFocusLabelColor = primaryText,
                        isPassword = true,
                        isError = emailError != null,
                        errorMessage = emailError?.let { stringResource(it) }
                    )
                    JPInput(
                        value = uiStateRegister.password,
                        contentColor = Color.White,
                        onValueChange = {
                            onUpdateData(EFieldType.PASSWORD, it)
                        },
                        label = stringResource(id = R.string.passwordLabel),
                        focusBorderColor = Color.White,
                        unFocusLabelColor = primaryText,
                        isPassword = true,
                        isError = passwordError != null,
                        errorMessage = passwordError?.let { stringResource(it) },
                        imeAction = ImeAction.Done,
                        onDone = {
                            onRegister()
                        }
                    )
                }

                JPButton(
                    label = stringResource(id = R.string.registerButton),
                    bgColor = Color.White,
                    textColor = Color.Black,
                    isBorder = true,
                    mTop = 48.dp
                ) { onRegister() }
                JPSecondaryButton(
                    mTop = 16.dp,
                    label = stringResource(id = R.string.loginWithGoogleButton),
                    imgButton = R.drawable.ic_google
                ) {}

                JPRow(mTop = 50.dp, isCenterHoz = true) {
                    JPText(
                        text = stringResource(id = R.string.registerAlreadyAccount),
                        mEnd = size_6
                    )
                    JPTextButton(text = stringResource(id = R.string.registerLogin)) {
                        onBack()
                    }
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun RegisterPreview() {
    RegisterScreen(UiState(), RegisterData())
}