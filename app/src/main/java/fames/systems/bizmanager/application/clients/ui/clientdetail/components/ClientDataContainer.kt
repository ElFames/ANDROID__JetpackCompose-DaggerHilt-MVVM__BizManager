package fames.systems.bizmanager.application.clients.ui.clientdetail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fames.systems.bizmanager.application.clients.domain.models.Client

@Composable
fun ClientDataContainer(client: Client?) {
    Text(
        modifier = Modifier.padding(start = 17.dp, top = 25.dp),
        text = "Información",
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif,
        fontSize = 19.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Black
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        border = BorderStroke(2.dp, color = Color.Gray)
    ) {
        ClientData(client)
    }
}

@Composable
fun ClientData(client: Client?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        InsertLine(client?.name, Icons.Default.AccountBox)
        InsertLine(client?.phone, Icons.Default.Phone)
        InsertLine(client?.email, Icons.Default.Email)
        InsertLine(client?.address, Icons.Default.Home)
    }
}

@Composable
fun InsertLine(property: String?, icon: ImageVector) {
    Row {
        Icon(
            modifier = Modifier.padding(top = 8.dp, end = 8.dp, start = 16.dp, bottom = 8.dp),
            imageVector = icon,
            contentDescription = "data_icon"
        )
        Text(
            modifier = Modifier.padding(10.dp),
            text = property ?: "",
            fontFamily = FontFamily.Serif,
            fontSize = 17.sp
        )
    }
}