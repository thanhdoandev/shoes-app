package com.example.compose_ui

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_ui.ui.navigations.AppNavigation
import com.example.compose_ui.ui.theme.Compose_uiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isSystemDark = isSystemInDarkTheme()
            val enableDarkMode = rememberSaveable { mutableStateOf(isSystemDark) }

            Compose_uiTheme(darkTheme = enableDarkMode.value) {
                AppNavigation(isSigned = viewModel.getIsSigned(), enableDarkMode = enableDarkMode)
            }
        }
    }
}

@Composable
fun JetPackComposeTest() {
}


@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun JetPackComposeTestPreview() {
    JetPackComposeTest()
}
