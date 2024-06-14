package com.example.compose_ui

import androidx.lifecycle.SavedStateHandle
import com.example.compose_ui.ui.bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {}