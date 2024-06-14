package com.example.compose_ui.ui.cores.data.generics.forms

interface FromValidationBase<In, Out> {
    fun execute(input: In): Out
}