package com.example.abal.presentation

sealed interface CalculatorEvent {
    data class LatFromChanged(val value: String) : CalculatorEvent
    data class LongFromChanged(val value: String) : CalculatorEvent
    data class LatToChanged(val value: String) : CalculatorEvent
    data class LongToChanged(val value: String) : CalculatorEvent
    object Calculate : CalculatorEvent
}