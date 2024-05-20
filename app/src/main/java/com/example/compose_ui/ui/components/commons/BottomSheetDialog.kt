package com.example.compose_ui.ui.components.commons

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_ui.ui.components.commons.apps.SearchInput
import com.example.compose_ui.ui.components.cores.JPButton
import com.example.compose_ui.ui.components.cores.JPColumn
import com.example.compose_ui.ui.components.cores.JPIcon
import com.example.compose_ui.ui.components.cores.JPRow
import com.example.compose_ui.ui.components.cores.JPSpacer
import com.example.compose_ui.ui.components.cores.JPText
import com.example.compose_ui.ui.data.vo.DropDownItem
import com.example.compose_ui.ui.extensions.onClickNoEffect
import com.example.compose_ui.ui.theme.font_16
import com.example.compose_ui.ui.theme.font_20
import com.example.compose_ui.ui.theme.none
import com.example.compose_ui.ui.theme.size_1
import com.example.compose_ui.ui.theme.size_12
import com.example.compose_ui.ui.theme.size_16
import com.example.compose_ui.ui.theme.size_20
import com.example.compose_ui.ui.theme.size_22
import com.example.compose_ui.ui.theme.size_6
import com.example.compose_ui.ui.utils.getDeviceSize

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetModal(
    isVisible: Boolean = false,
    items: MutableList<DropDownItem> = mutableListOf(),
    percentHeight: Int = 2,
    isSearch: Boolean = false,
    isCheckbox: Boolean = false,
    onClickItem: (item: DropDownItem) -> Unit = {},
    onButtonAction: (items: MutableList<DropDownItem>) -> Unit = {},
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val itemSelected: MutableList<DropDownItem> by rememberSaveable {
        mutableStateOf(mutableListOf())
    }

    AnimatedVisibility(visible = isVisible) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            modifier = Modifier
                .height((getDeviceSize().height / percentHeight).dp)
                .fillMaxWidth(),
            sheetState = sheetState
        ) {
            JPText(
                text = "Setting Language",
                isCenter = true,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = font_20,
                    fontWeight = FontWeight.Bold
                )
            )
            HorizontalDivider(thickness = size_1, modifier = Modifier.padding(none, size_16))
            JPColumn(
                pHoz = size_20,
                modifier = Modifier.weight(1f)
            ) {
                if (isSearch) SearchInput(mTop = size_6) {}
                LazyColumn {
                    items(items.size) {
                        items.getOrNull(it)?.let { item ->
                            DropDownElement(
                                index = it,
                                item = item,
                                isCheckbox
                            ) {
                                if (isCheckbox) {
                                    itemSelected.run {
                                        if (contains(item)) remove(item) else add(item)
                                    }
                                } else onClickItem(item)
                            }
                        }
                    }
                }

            }
            JPButton(
                label = "Hello",
                modifier = Modifier.align(Alignment.End),
                mHoz = size_20
            ) {
                Log.i("xxxx___", itemSelected.toString())
                onButtonAction(itemSelected)
            }
            JPSpacer(h = size_20)
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun BottomSheetDialogPreview() {
    BottomSheetModal(isVisible = true) {}
}

@Composable
private fun DropDownElement(
    index: Int = 0,
    item: DropDownItem,
    isCheckbox: Boolean = true,
    onClick: () -> Unit = {}
) {
    var isChecked by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(item.isSelected) {
        isChecked = item.isSelected
    }

    item.run {
        JPColumn(Modifier.onClickNoEffect { onClick() }) {
            if (index != 0) HorizontalDivider(thickness = size_1)
            JPRow(pVer = size_12, isCenterVer = true) {
                JPText(
                    text = displayName,
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = font_16),
                    modifier = Modifier.weight(1f)
                )
                if (isCheckbox) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = {
                            onClick()
                            isChecked = !isChecked
                        },
                        colors = CheckboxDefaults.colors(checkedColor = Color.Red)
                    )
                }
                if (!isCheckbox && isSelected) JPIcon(
                    icon = Icons.Filled.CheckCircle,
                    color = Color.Red,
                    size = size_22
                )
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun DropDownElementPreview() {
    DropDownElement(item = DropDownItem(id = "1", displayName = "Doan Tien Thanh"))
}