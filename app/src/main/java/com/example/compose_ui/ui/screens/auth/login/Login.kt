package com.example.compose_ui.ui.screens.auth.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_ui.R
import com.example.compose_ui.ui.components.bases.ContainerPage
import com.example.compose_ui.ui.components.bases.UIState
import com.example.compose_ui.ui.components.cores.JPButton
import com.example.compose_ui.ui.components.cores.JPCard
import com.example.compose_ui.ui.components.cores.JPInput
import com.example.compose_ui.ui.components.cores.JPRow
import com.example.compose_ui.ui.components.cores.JPSecondaryButton
import com.example.compose_ui.ui.components.cores.JPSpacer
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.components.cores.JPTextButton
import com.example.compose_ui.ui.data.enums.EFieldType
import com.example.compose_ui.ui.extensions.Keyboard
import com.example.compose_ui.ui.extensions.keyboardAsState
import com.example.compose_ui.ui.theme.none
import com.example.compose_ui.ui.theme.primaryColor
import com.example.compose_ui.ui.theme.primaryText
import com.example.compose_ui.ui.theme.size_16
import com.example.compose_ui.ui.theme.size_28
import com.example.compose_ui.ui.theme.size_4
import com.example.compose_ui.ui.theme.size_40
import com.example.compose_ui.ui.theme.size_48
import com.example.compose_ui.ui.theme.size_50
import com.example.compose_ui.ui.theme.size_6
import com.example.compose_ui.ui.theme.size_80

@Composable
fun Login(
    viewModel: LoginViewModel = hiltViewModel(),
    onRegister: () -> Unit = {},
    onOpenHome: () -> Unit = {}
) {
    val isLoginSuccess by viewModel.isLoginSuccess.collectAsState()

    LaunchedEffect(isLoginSuccess) {
        if (isLoginSuccess) {
            onOpenHome()
        }
    }

    viewModel.run {
        LoginScreen(
            uiState = uiState.collectAsState().value,
            uiData = loginState.collectAsState().value,
            onUpdateData = { field, data ->
                onLoginEvent(
                    if (field == EFieldType.EMAIL) {
                        LoginEvent.EmailChanged(data)
                    } else {
                        LoginEvent.PasswordChanged(data)
                    }
                )
            },
            onLogin = {
                if (!it) onLoginEvent(LoginEvent.Submit)
            },
            onRegister = onRegister
        )
    }
}

@Composable
private fun LoginScreen(
    uiState: UIState,
    uiData: LoginData,
    onUpdateData: (field: EFieldType, data: String) -> Unit = { _, _ -> },
    onLogin: (isSocial: Boolean) -> Unit = {},
    onRegister: () -> Unit = {}
) {
    val isFocusedInput by keyboardAsState()

    ContainerPage(uiState = uiState) {
        if (isFocusedInput == Keyboard.Closed) JPSpacer(h = size_80)
        JPCard(
            roundTopStart = size_28,
            roundTopEnd = size_28,
            isMaxSize = true,
            contentColor = Color.White,
            bgColor = primaryColor,
            padding = none
        ) {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(size_16, none)
            ) {
                item {
                    JPText(
                        modifier = Modifier.fillMaxWidth(),
                        mTop = size_40,
                        text = stringResource(id = R.string.loginTitle),
                        isCenter = true,
                        style = MaterialTheme.typography.titleLarge
                    )
                    JPText(
                        mTop = size_16,
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.loginDesc),
                        isCenter = true
                    )
                    JPInput(
                        value = uiData.email,
                        onValueChange = {
                            onUpdateData(EFieldType.EMAIL, it)
                        },
                        label = stringResource(id = R.string.emailLabel),
                        mTop = size_28,
                        focusBorderColor = Color.White,
                        contentColor = Color.White,
                        unFocusLabelColor = primaryText,
                        isError = uiData.emailError != null,
                        errorMessage = uiData.emailError?.let { stringResource(it) }
                    )
                    JPInput(
                        value = uiData.password,
                        contentColor = Color.White,
                        onValueChange = {
                            onUpdateData(EFieldType.PASSWORD, it)
                        },
                        label = stringResource(id = R.string.passwordLabel),
                        focusBorderColor = Color.White,
                        unFocusLabelColor = primaryText,
                        isPassword = true,
                        isError = uiData.passwordError != null,
                        errorMessage = uiData.passwordError?.let { stringResource(it) },
                        imeAction = ImeAction.Done,
                        onDone = {
                            onLogin(false)
                        }
                    )
                    JPTextButton(
                        mTop = size_4,
                        text = stringResource(id = R.string.loginRecoveryPassword),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End
                    ) {}
                    JPButton(
                        label = stringResource(id = R.string.loginButton),
                        bgColor = Color.White,
                        textColor = Color.Black,
                        mTop = size_48
                    ) { onLogin(false) }

                    JPSecondaryButton(
                        mTop = size_16,
                        label = stringResource(id = R.string.loginWithGoogleButton),
                        imgButton = R.drawable.ic_google
                    ) {}

                    JPRow(mTop = size_50, isCenterHoz = true) {
                        JPText(text = stringResource(id = R.string.loginNewUser), mEnd = size_6)
                        JPTextButton(
                            text = stringResource(id = R.string.loginCreateAccount),
                            color = Color.White
                        ) {
                            onRegister()
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun LoginPreview() {
    LoginScreen(UIState(), LoginData())
}