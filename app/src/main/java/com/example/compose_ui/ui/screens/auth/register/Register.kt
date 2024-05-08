package com.example.compose_ui.ui.screens.auth.register

import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_ui.R
import com.example.compose_ui.ui.components.bases.ContainerPage
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
import com.example.compose_ui.ui.data.enums.EFieldType
import com.example.compose_ui.ui.theme.primaryText
import com.example.compose_ui.ui.theme.size_16
import com.example.compose_ui.ui.theme.size_28
import com.example.compose_ui.ui.theme.size_6

@Composable
fun Register(
    onBack: () -> Boolean? = { false },
    onOpenHome: () -> Unit = {},
    viewModel: RegisterViewModels = hiltViewModel()
) {
    val userInfo by viewModel.user.collectAsState()
    val isSuccess by viewModel.isSuccess.collectAsState()

    fun onUserChange(type: EFieldType, value: String) {
        viewModel.userDataChange(type, value)
    }

    LaunchedEffect(isSuccess) {
        if (isSuccess) {
            Log.i("xxxx++++", "vao day")
            onOpenHome()
        }
    }

    ContainerPage(uiState = viewModel.uiState.collectAsState().value) {
        JPColumn {
            JPSpacer(h = size_28)
            JPIcon(icon = Icons.Default.ArrowBackIosNew, mStart = size_16, onClick = { onBack() })
            JPSpacer(h = size_28)
            JPCard(
                isMaxSize = true,
                roundTopStart = 28.dp,
                roundTopEnd = 28.dp
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
                JPInput(
                    value = userInfo.name,
                    onValueChange = {
                        onUserChange(EFieldType.YOUR_NAME, it)
                    },
                    label = stringResource(id = R.string.registerYourName),
                    mTop = 40.dp,
                    focusBorderColor = Color.White,
                    contentColor = Color.White,
                    unFocusLabelColor = primaryText
                )
                JPInput(
                    value = userInfo.email,
                    contentColor = Color.White,
                    onValueChange = {
                        onUserChange(EFieldType.EMAIL, it)
                    },
                    label = stringResource(id = R.string.emailLabel),
                    focusBorderColor = Color.White,
                    unFocusLabelColor = primaryText,
                    isPassword = true
                )
                JPInput(
                    value = userInfo.password,
                    contentColor = Color.White,
                    onValueChange = {
                        onUserChange(EFieldType.PASSWORD, it)
                    },
                    label = stringResource(id = R.string.passwordLabel),
                    focusBorderColor = Color.White,
                    unFocusLabelColor = primaryText,
                    isPassword = true
                )
                JPButton(
                    label = stringResource(id = R.string.registerButton),
                    bgColor = Color.White,
                    textColor = Color.Black,
                    isBorder = true,
                    mTop = 48.dp
                ) {
                    viewModel.registerAccount()
                }
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
    Register()
}