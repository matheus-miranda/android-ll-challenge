package com.luizalabs.registration.presentation.list.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.luizalabs.registration.R
import com.luizalabs.ui.debouncedClick

@Composable
fun FabButton(onClick: () -> Unit) {
    FloatingActionButton(onClick = debouncedClick { onClick.invoke() }) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_new_form)
        )
    }
}
