package com.example.abal.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.abal.R

@Composable
fun DistanceCalculator(modifier: Modifier = Modifier,
                       viewModel: HomeViewModel,
                       onEvent: (CalculatorEvent) -> Unit = {}) {

    val viewmodel = viewModel.uiState
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(
            text = stringResource(R.string.from),
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.Start)
        )
        TextField(
            modifier = Modifier.padding(start = 8.dp),
            label = { Text(text = stringResource(R.string.latitude)) },
            singleLine = true,
            value = viewmodel.latFrom?.toString() ?: "",
            onValueChange = { onEvent(CalculatorEvent.LatFromChanged(it)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onAny = {
                focusManager.moveFocus(FocusDirection.Next)
            }),
            enabled = !viewmodel.isCalculating
            )

        TextField(
            modifier = Modifier.padding(start = 8.dp),
            label = { Text(text = stringResource(R.string.longitude)) },
            singleLine = true,
            value = viewmodel.lonFrom ?: "",
            onValueChange = { onEvent(CalculatorEvent.LongFromChanged(it))  },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onAny = {
                focusManager.moveFocus(FocusDirection.Next)
            }),
            enabled = !viewmodel.isCalculating
        )

        Text(
            text = stringResource(R.string.to),
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.Start)
        )
        TextField(
            modifier = Modifier.padding(start = 8.dp),
            label = { Text(text = stringResource(R.string.latitude)) },
            singleLine = true,
            value = viewmodel.latTo ?: "",
            onValueChange = { onEvent(CalculatorEvent.LatToChanged(it))},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onAny = {
                focusManager.moveFocus(FocusDirection.Next)
            }),
            enabled = !viewmodel.isCalculating
        )

        TextField(
            modifier = Modifier.padding(start = 8.dp),
            label = { Text(text = stringResource(R.string.longitude)) },
            value = viewmodel.lonTo ?: "",
            singleLine = true,
            onValueChange = { onEvent(CalculatorEvent.LongToChanged(it)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onAny = {
                focusManager.moveFocus(FocusDirection.Next)
            }),
            enabled = !viewmodel.isCalculating
        )

        //Button to calculate distance
        Box(contentAlignment = Alignment.Center) {
            if (viewmodel.isCalculating) {
                CircularProgressIndicator(modifier = Modifier.padding(4.dp))
            }

            OutlinedButton(onClick = { onEvent(CalculatorEvent.Calculate) },
                enabled = !viewmodel.isCalculating) {
                Text(text = "Calculate")
            }
        }

        Text("Result", textDecoration = TextDecoration.Underline)
        viewmodel.result?.let {
            Text(text = stringResource(id = R.string.distance_is), maxLines = 2)

            val str = viewmodel.result
            Text(
                modifier = Modifier.padding(4.dp),
                text = str,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
