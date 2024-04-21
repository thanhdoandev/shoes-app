package com.example.compose_ui.ui.screens.auth.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_ui.R
import com.example.compose_ui.ui.components.bases.ContainerPage
import com.example.compose_ui.ui.components.cores.JPButton
import com.example.compose_ui.ui.components.cores.JPCard
import com.example.compose_ui.ui.components.cores.JPColumn
import com.example.compose_ui.ui.components.cores.JPInput
import com.example.compose_ui.ui.components.cores.JPRow
import com.example.compose_ui.ui.components.cores.JPSecondaryButton
import com.example.compose_ui.ui.components.cores.JPSpacer
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.components.cores.JPTextButton
import com.example.compose_ui.ui.data.enums.EFieldType
import com.example.compose_ui.ui.theme.primaryText
import com.example.compose_ui.ui.theme.size_6


@Composable
fun Login(
    viewModel: LoginViewModel = hiltViewModel(),
    onRegister: () -> Unit = {},
    onOpenHome: () -> Unit = {}
) {
    val loginData by viewModel.loginData.collectAsState()
    val isLoginSuccess by viewModel.isLoginSuccess.collectAsState()

    LaunchedEffect(isLoginSuccess) {
        if (isLoginSuccess) {
            onOpenHome()
        }
    }

    ContainerPage(
        isVisibleHeader = false,
        uiState = viewModel.uiState.collectAsState().value
    ) {
        JPSpacer(h = 80.dp)
        JPCard(
            roundTopStart = 28.dp,
            roundTopEnd = 28.dp,
            isMaxSize = true
        ) {
            JPColumn(Modifier.verticalScroll(rememberScrollState())) {
                JPText(
                    modifier = Modifier.fillMaxWidth(),
                    mTop = 40.dp,
                    text = stringResource(id = R.string.loginTitle),
                    isCenter = true,
                    style = MaterialTheme.typography.titleLarge
                )
                JPText(
                    mTop = 4.dp,
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.loginDesc),
                    isCenter = true
                )
                JPInput(
                    value = loginData.email,
                    onValueChange = {
                        viewModel.updateLogin(EFieldType.EMAIL, it)
                    },
                    label = stringResource(id = R.string.emailLabel),
                    mTop = 40.dp,
                    focusBorderColor = Color.White,
                    contentColor = Color.White,
                    unFocusLabelColor = primaryText
                )
                JPInput(
                    value = loginData.password,
                    contentColor = Color.White,
                    onValueChange = {
                        viewModel.updateLogin(EFieldType.PASSWORD, it)
                    },
                    label = stringResource(id = R.string.passwordLabel),
                    focusBorderColor = Color.White,
                    unFocusLabelColor = primaryText,
                    isPassword = true
                )
                JPTextButton(
                    mTop = 4.dp,
                    text = stringResource(id = R.string.loginRecoveryPassword),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                ) {}
                JPButton(
                    label = stringResource(id = R.string.loginButton),
                    bgColor = Color.White,
                    textColor = Color.Black,
                    isBorder = true,
                    mTop = 48.dp
                ) { viewModel.onLogin() }

                JPSecondaryButton(
                    mTop = 16.dp,
                    label = stringResource(id = R.string.loginWithGoogleButton),
                    imgButton = R.drawable.ic_google
                ) {}

                JPRow(mTop = 50.dp, isCenterHoz = true) {
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

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun LoginPreview() {
    Login()
}