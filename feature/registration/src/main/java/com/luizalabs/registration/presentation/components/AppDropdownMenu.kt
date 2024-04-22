package com.luizalabs.registration.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDropdownMenu(
    value: String,
    label: String,
    itemList: List<String>,
    enabled: Boolean = true,
    onSelectedItem: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expandedMenuState by remember { mutableStateOf(false) }
    var selectedOptionTextState by remember { mutableStateOf(String()) }

    ExposedDropdownMenuBox(
        expanded = expandedMenuState,
        onExpandedChange = { expandedMenuState = it },
    ) {
        TextField(
            value = value,
            enabled = enabled,
            label = { Text(label) },
            onValueChange = {},
            readOnly = true,
            trailingIcon = { TrailingIcon(expanded = expandedMenuState) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .then(modifier)
        )

        DropdownMenu(
            expanded = expandedMenuState,
            onDismissRequest = { expandedMenuState = false },
            modifier = Modifier.exposedDropdownSize()
        ) {
            itemList.forEach { selectedOption ->
                DropdownMenuItem(
                    text = { Text(text = selectedOption) },
                    onClick = {
                        selectedOptionTextState = selectedOption
                        expandedMenuState = false
                        onSelectedItem.invoke(selectedOption)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}
