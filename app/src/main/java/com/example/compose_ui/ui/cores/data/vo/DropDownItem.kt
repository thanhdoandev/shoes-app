package com.example.compose_ui.ui.cores.data.vo

data class DropDownItem(
    val id: String,
    var displayName: String,
    var name: String? = null,
    var isSelected: Boolean = false
)