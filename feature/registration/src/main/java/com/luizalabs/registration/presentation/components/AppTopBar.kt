package com.luizalabs.registration.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.luizalabs.registration.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppTopBar(
    title: String,
    shouldNavigateUp: Boolean = false,
    onNavigateUp: (() -> Unit)? = null,
    scrollBehavior: TopAppBarScrollBehavior,
    shouldShowDelete: Boolean = false,
    onDeleteClick: (() -> Unit)? = null,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title, overflow = TextOverflow.Ellipsis, maxLines = 1) },
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (shouldNavigateUp) {
                IconButton(onClick = { onNavigateUp?.invoke() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        },
        actions = {
            if (shouldShowDelete) {
                IconButton(onClick = { onDeleteClick?.invoke() }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = stringResource(R.string.delete)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun AppTopBarPreview() {
    AppTopBar(
        title = "App Bar Title",
        shouldNavigateUp = true,
        shouldShowDelete = true,
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    )
}
