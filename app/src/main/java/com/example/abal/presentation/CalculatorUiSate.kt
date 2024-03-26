package com.example.abal.presentation

data class CalculatorUiSate(
    val latFrom: String? = null,
    val latTo: String? = null,
    val lonFrom: String? = null,
    val lonTo: String? = null,
    val result: String? = null,
    val isCalculating: Boolean = false
)