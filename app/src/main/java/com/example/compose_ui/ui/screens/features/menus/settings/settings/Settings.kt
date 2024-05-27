package com.example.compose_ui.ui.screens.features.menus.settings.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.LocaleListCompat
import com.example.compose_ui.R
import com.example.compose_ui.ui.components.bases.ContainerPage
import com.example.compose_ui.ui.components.commons.apps.BottomSheetModal
import com.example.compose_ui.ui.components.cores.JPCard
import com.example.compose_ui.ui.components.cores.JPColumn
import com.example.compose_ui.ui.components.cores.JPIcon
import com.example.compose_ui.ui.components.cores.JPRow
import com.example.compose_ui.ui.components.cores.JPSpacer
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.data.enums.EScreenName
import com.example.compose_ui.ui.data.vo.DropDownItem
import com.example.compose_ui.ui.screens.features.menus.settings.components.SettingItem
import com.example.compose_ui.ui.theme.font_20
import com.example.compose_ui.ui.theme.primaryColor
import com.example.compose_ui.ui.theme.size_16
import com.example.compose_ui.ui.theme.size_20
import com.example.compose_ui.ui.theme.size_32
import com.example.compose_ui.ui.theme.size_40
import com.example.compose_ui.ui.theme.size_6

sealed class SettingItem(
    val title: Int,
    val route: EScreenName,
    val icon: ImageVector,
    val isTextEnd: Boolean = false
) {
    data object Language : SettingItem(
        title = R.string.settingLanguage,
        route = EScreenName.LANGUAGE,
        icon = Icons.Default.Language,
        isTextEnd = true
    )

    data object PrivacyAndSecurity : SettingItem(
        title = R.string.settingPrivacyAndSecurity,
        route = EScreenName.PRIVACY_SECURITY,
        icon = Icons.Default.PrivacyTip
    )

    data object Support : SettingItem(
        title = R.string.settingSupport,
        route = EScreenName.SUPPORT,
        icon = Icons.Default.SupportAgent
    )

    data object About : SettingItem(
        title = R.string.settingAbout,
        route = EScreenName.ABOUT,
        icon = Icons.Default.Info
    )
}


val SETTING_ITEMS =
    listOf(
        SettingItem.Language,
        SettingItem.PrivacyAndSecurity,
        SettingItem.Support,
        SettingItem.About
    )

@Composable
fun Settings(onOpenMenu: () -> Unit) {
    val languages: MutableList<DropDownItem> = rememberSaveable {
        mutableListOf(
            DropDownItem(
                id = "en",
                displayName = "English"
            ),
            DropDownItem(
                id = "vi",
                displayName = "Việt Nam"
            ),
            DropDownItem(
                id = "zh",
                displayName = "Chinese"
            ),
            DropDownItem(
                id = "et",
                displayName = "Estonian"
            )
        )
    }

    var currentLanguage by rememberSaveable {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        val language = androidx.compose.ui.text.intl.Locale.current
        languages.firstOrNull { it.id == language.language }?.apply {
            currentLanguage = displayName
            isSelected = true
        }
    }

    fun changeLanguage(language: DropDownItem) {
        currentLanguage = language.displayName
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(language.id))
        languages.forEach {
            it.apply {
                isSelected = it.id == language.id
            }
        }
    }

    JPColumn(Modifier.fillMaxSize()) {
        SettingScreen(
            languages = languages,
            currentLanguage = currentLanguage,
            onChangeLanguage = {
                changeLanguage(it)
            }) {
            onOpenMenu()
        }
    }
}

@Composable
private fun SettingScreen(
    languages: MutableList<DropDownItem> = mutableListOf(),
    onChangeLanguage: (language: DropDownItem) -> Unit = {},
    currentLanguage: String = "",
    onOpenMenu: () -> Unit = {}
) {
    var isLanguageSetting by rememberSaveable {
        mutableStateOf(false)
    }


    ContainerPage(
        stringResource(id = R.string.settingLabel),
        isMenu = true,
        onBackScreen = onOpenMenu,
        bgColor = primaryColor
    ) {
        JPColumn(mHoz = size_16) {
            JPRow(mVer = size_32, isCenterVer = true) {
                JPIcon(icon = Icons.Default.Settings, size = size_40, color = Color.White)
                JPText(
                    text = stringResource(id = R.string.settingLabel),
                    mStart = size_6,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = font_20,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White
                )
            }
            JPSpacer(h = size_20)
            JPCard(
                bgColor = MaterialTheme.colorScheme.background,
                modifier = Modifier.fillMaxSize(),
                roundTopStart = size_16,
                roundTopEnd = size_16,
                contentColor = contentColorFor(MaterialTheme.colorScheme.background)
            ) {
                SETTING_ITEMS.forEachIndexed { index, settingItem ->
                    SettingItem(settingItem, index, currentLanguage) {
                        if (settingItem.isTextEnd) isLanguageSetting = true
                    }
                }
            }
        }

        BottomSheetModal(
            isVisible = isLanguageSetting,
            items = languages,
            onClickItem = {
                isLanguageSetting = false
                if (it.displayName != currentLanguage) onChangeLanguage(it)
            }
        ) {
            isLanguageSetting = false
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun SettingScreenPreview() {
    SettingScreen()
}