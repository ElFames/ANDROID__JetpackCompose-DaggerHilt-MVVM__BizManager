package fames.systems.bizmanager.application.tpvpos.ui.pointofsale.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fames.systems.bizmanager.application.clients.domain.models.Client
import fames.systems.bizmanager.application.tpvpos.ui.pointofsale.TpvPosViewModel

@Composable
fun ClientSelectedInfo(
    clientSelected: Client?,
    viewModel: TpvPosViewModel,
    navigateClientsScreen: () -> Unit,
) {
    var textClientSelected = "Cliente seleccionado: ${clientSelected?.name}"
    if (clientSelected == null) textClientSelected = "Ningún cliente seleccionado"

    Row(
        modifier = Modifier.fillMaxWidth().border(1.dp, Color.DarkGray).padding(13.dp)
            .clickable {
                navigateClientsScreen()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = textClientSelected,
            color = Color.Black,
            fontSize = 14.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Black,
            fontFamily = FontFamily.Serif
        )
        Image(
            modifier = Modifier
                .size(24.dp)
                .clickable { viewModel.clearClientSelected() },
            imageVector = Icons.Default.Clear,
            contentDescription = "clear_icon"
        )
    }
}