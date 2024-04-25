package com.luizalabs.registration.presentation.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.luizalabs.registration.R
import com.luizalabs.registration.domain.model.DeliveryForm
import com.luizalabs.registration.presentation.common.toDateString
import com.luizalabs.registration.presentation.list.ListUiState
import com.luizalabs.ui.debouncedClick

@Composable
fun DeliveryList(
    innerPadding: PaddingValues,
    uiState: ListUiState.Success,
    onViewCardDetailsClick: (Int) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(bottom = 8.dp)
    ) {
        items(uiState.list, key = { it.id }) { form ->
            ListItem(form, onViewCardDetailsClick)
        }
    }
}

@Composable
private fun ListItem(form: DeliveryForm, onViewCardDetailsClick: (Int) -> Unit) {
    Card(
        onClick = debouncedClick { onViewCardDetailsClick.invoke(form.id) },
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Text(
                text = stringResource(
                    R.string.delivery_deadline,
                    form.deliveryDeadline.toDateString()
                ),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(R.string.street, form.address.street))
            Text(text = stringResource(R.string.number, form.address.number))
            Text(text = stringResource(R.string.neighborhood, form.address.neighborhood))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "${form.address.city}, ")
                Text(text = form.address.state)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(R.string.customer, form.clientName))
        }
    }
}
