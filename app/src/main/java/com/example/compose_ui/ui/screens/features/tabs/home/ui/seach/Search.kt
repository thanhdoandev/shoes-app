package com.example.compose_ui.ui.screens.features.tabs.home.ui.seach

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_ui.R
import com.example.compose_ui.ui.components.bases.ContainerPage
import com.example.compose_ui.ui.components.commons.products.ProductCardRow
import com.example.compose_ui.ui.components.commons.apps.SearchInput
import com.example.compose_ui.ui.components.cores.JPColumn
import com.example.compose_ui.ui.components.cores.JPRow
import com.example.compose_ui.ui.components.cores.JPSpacer
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.components.cores.Loading
import com.example.compose_ui.ui.data.vo.Product
import com.example.compose_ui.ui.screens.features.tabs.home.ui.seach.components.HistorySearch
import com.example.compose_ui.ui.screens.features.tabs.home.ui.seach.components.MicroAnimation
import com.example.compose_ui.ui.theme.none
import com.example.compose_ui.ui.theme.primaryColor
import com.example.compose_ui.ui.theme.size_10
import com.example.compose_ui.ui.theme.size_16
import com.example.compose_ui.ui.theme.size_20
import com.example.compose_ui.ui.theme.size_28
import com.example.compose_ui.ui.theme.size_40
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Search(
    viewModel: SearchViewModel = hiltViewModel(),
    onBack: () -> Unit = {},
    onClickItem: (id: String) -> Unit
) {
    val isMicroPhoneRunning by rememberSaveable {
        mutableStateOf(false)
    }

    val voicePermissionState = rememberPermissionState(android.Manifest.permission.RECORD_AUDIO)

    val speechRecognizerLauncher = rememberLauncherForActivityResult(
        contract = SpeechRecognizerContract(),
        onResult = {
            it?.firstOrNull()?.let(viewModel::searchShoes)
        }
    )

    fun microPhoneHandle() {
        if (voicePermissionState.status.isGranted) {
            speechRecognizerLauncher.launch(Unit)
        } else {
            voicePermissionState.launchPermissionRequest()
        }
    }


    viewModel.run {
        SearchScreen(
            isLoading = isLoading.collectAsState().value,
            shoesList = shoesList.collectAsState().value,
            searchValue = searchValue.collectAsState().value,
            isMicroPhoneRunning = isMicroPhoneRunning,
            histories = getHistories(),
            onBack = onBack,
            onClickItem = onClickItem,
            onClickMicroPhone = {
                microPhoneHandle()
            }
        ) {
            searchShoes(it)
        }
    }
}

@Composable
private fun SearchScreen(
    isLoading: Boolean = false,
    searchValue: String = "",
    isMicroPhoneRunning: Boolean = false,
    shoesList: MutableList<Product> = mutableListOf(),
    histories: MutableList<String> = mutableListOf(),
    onBack: () -> Unit = {},
    onClickItem: (id: String) -> Unit = {},
    onClickMicroPhone: () -> Unit = {},
    onSearch: (text: String) -> Unit = {}
) {
    ContainerPage(title = stringResource(id = R.string.searchLabel), onBackScreen = { onBack() }) {
        JPColumn(pHoz = size_20) {
            JPSpacer(h = size_40)
            SearchInput(
                mTop = none,
                hint = "Search ....",
                value = searchValue,
                icon = Icons.Default.Mic,
                mHoz = size_16,
                onClickIcon = onClickMicroPhone
            ) {
                onSearch(it)
            }
            if (!isMicroPhoneRunning) JPText(
                text = stringResource(id = R.string.shoesLabel), mTop = size_10,
                style = MaterialTheme.typography.titleMedium.copy(color = primaryColor)
            )
        }

        if (!isMicroPhoneRunning) LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(size_16)
        ) {
            item {
                AnimatedVisibility(
                    visible = isLoading,
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it })
                ) {
                    JPRow(isCenterHoz = true, mTop = size_20) {
                        Loading(size = size_28)
                    }
                }
            }

            items(if (shoesList.isEmpty()) histories else shoesList) {
                AnimatedVisibility(
                    visible = !isLoading,
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it })
                ) {
                    if (shoesList.isEmpty()) HistorySearch(item = it as String) {
                        onSearch(it)
                    } else ProductCardRow(it as Product) { id ->
                        onClickItem(id)
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = isMicroPhoneRunning,
            enter = slideInVertically(),
            exit = slideOutVertically()
        ) {
            MicroAnimation {

            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun SearchScreenPreview() {
    SearchScreen()
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun SearchLoadingScreenPreview() {
    SearchScreen(isLoading = true)
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun SearchScreenMicroPhonePreview() {
    SearchScreen(isMicroPhoneRunning = true)
}