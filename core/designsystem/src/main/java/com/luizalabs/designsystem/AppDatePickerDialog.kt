package com.luizalabs.designsystem

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDatePickerDialog(
    onDateSelected: (Long) -> Unit,
    onDismiss: () -> Unit,
) {
    val datePickerState = rememberDatePickerState(
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis >= System.currentTimeMillis()
            }
        }
    )

    val selectedDate = datePickerState.selectedDateMillis ?: System.currentTimeMillis()
    val adjustedDate = selectedDate + TimeUnit.DAYS.toMillis(1)

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(
                onClick = {
                    onDateSelected(adjustedDate)
                    onDismiss()
                }
            ) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            Button(onClick = {
                onDismiss()
            }) {
                Text(text = stringResource(R.string.cancel))
            }
        }
    ) {
        DatePicker(
            state = datePickerState
        )
    }
}
