package com.example.compose_ui.ui.screens.auth.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_ui.R
import com.example.compose_ui.ui.components.bases.ContainerPage
import com.example.compose_ui.ui.components.cores.JPButton
import com.example.compose_ui.ui.components.cores.JPInput
import com.example.compose_ui.ui.components.cores.JPSecondaryButton
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.components.cores.JPTextButton
import com.example.compose_ui.ui.data.enums.EFieldType
import com.example.compose_ui.ui.theme.primaryColor
import com.example.compose_ui.ui.theme.primaryText
import com.example.compose_ui.ui.theme.secondaryText

@Composable
fun Register(
    onBack: () -> Boolean? = { false },
    viewModel: RegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val userInfo by viewModel.user.collectAsState()

    fun onUserChange(type: EFieldType, value: String) {
        viewModel.userDataChange(type, value)
    }

    ContainerPage(
        isScrollPage = true,
        isVisibleHeaderLine = false,
        onBackScreen = {
            onBack()
        }
    ) {
        Column(
            modifier = Modifier
                .weight(10f)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            JPText(
                mTop = 16.dp,
                text = stringResource(id = R.string.registerTitle),
                size = 40.sp,
                fontWeight = FontWeight.Bold
            )

            JPText(
                text = stringResource(id = R.string.loginDesc),
                size = 20.sp,
                isCenter = true,
                color = secondaryText,
                mTop = 6.dp
            )

            JPInput(
                label = stringResource(id = R.string.registerYourName),
                value = userInfo.name,
                mTop = 28.dp
            ) {
                onUserChange(EFieldType.YOUR_NAME, it)
            }

            JPInput(
                label = "${stringResource(id = R.string.emailLabel)} *",
                value = userInfo.email
            ) {
                onUserChange(EFieldType.EMAIL, it)
            }

            JPInput(
                value = userInfo.password,
                label = "${stringResource(id = R.string.passwordLabel)} *",
                isPassword = true
            ) {
                onUserChange(EFieldType.PASSWORD, it)
            }

            Row {
                Spacer(modifier = Modifier.weight(1f))
                JPTextButton(
                    mTop = 2.dp,
                    text = stringResource(id = R.string.loginRecoveryPassword),
                    textAlign = TextAlign.End,
                    color = primaryColor,
                    size = 15.sp,
                ) {
                }
            }
            JPButton(
                label = stringResource(id = R.string.loginButton),
                mTop = 60.dp
            ) {

            }

            JPSecondaryButton(
                label = stringResource(id = R.string.loginWithGoogleButton),
                imgButton = R.drawable.ic_google,
                mTop = 20.dp

            ) {

            }
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                JPText(
                    text = stringResource(id = R.string.registerAlreadyAccount),
                    size = 16.sp,
                    color = secondaryText
                )
                Spacer(modifier = Modifier.width(6.dp))
                JPTextButton(
                    text = stringResource(id = R.string.registerLogin),
                    size = 16.sp,
                    color = primaryText,
                    fontWeight = FontWeight.Medium
                ) { onBack() }
            }
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun RegisterPreview() {
    Register()
}