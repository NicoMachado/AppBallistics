package com.example.abal.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abal.util.formatDistance
import com.example.lcore.domain.usescases.CalculateDistanceUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel (
    private val calculateDistanceUseCase: CalculateDistanceUseCase
): ViewModel() {

    var uiState by mutableStateOf(CalculatorUiSate())
        private set

    fun onEvent(event: CalculatorEvent) {
        when (event) {
            CalculatorEvent.Calculate -> {
                uiState = uiState.copy(
                    isCalculating = true
                )
                viewModelScope.launch {
                    delay(3000)     //Simulate a calculation on background

                    uiState = uiState.copy(
                        result = calculate(),
                        isCalculating = false
                    )
                }
            }
            is CalculatorEvent.LatFromChanged -> {
                uiState = uiState.copy(
                    latFrom = event.value
                )
            }
            is CalculatorEvent.LatToChanged -> {
                uiState = uiState.copy(
                    latTo = event.value
                )
            }
            is CalculatorEvent.LongFromChanged -> {
                uiState = uiState.copy(
                    lonFrom = event.value
                )
            }
            is CalculatorEvent.LongToChanged -> {
                uiState = uiState.copy(
                    lonTo = event.value
                )
            }
        }
    }

    private fun calculate() : String {

        if (uiState.latTo == null ||
            uiState.lonTo == null ||
            uiState.latFrom == null ||
            uiState.lonFrom == null)
            return "Please fill all fields"

        return try {
            calculateDistanceUseCase(uiState.latFrom!!.toDouble(),
                uiState.lonFrom!!.toDouble(),
                uiState.latTo!!.toDouble(),
                uiState.lonTo!!.toDouble(),
            ).formatDistance()

        } catch (e: Exception) {
            return "Exception"
        } catch (n: NumberFormatException) {
            return "NumberFormatException"
        }
    }
}

