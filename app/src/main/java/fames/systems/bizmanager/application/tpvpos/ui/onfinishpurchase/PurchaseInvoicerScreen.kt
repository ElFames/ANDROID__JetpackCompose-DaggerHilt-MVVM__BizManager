package fames.systems.bizmanager.application.tpvpos.ui.onfinishpurchase

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import fames.systems.bizmanager.application.auth.ui.register.components.AlertDialogError
import fames.systems.bizmanager.application.clients.domain.models.Client
import fames.systems.bizmanager.application.products.domain.models.Product
import fames.systems.bizmanager.application.tpvpos.ui.onfinishpurchase.components.ClientInfoStamp
import fames.systems.bizmanager.application.tpvpos.ui.onfinishpurchase.components.DateTimeStamp
import fames.systems.bizmanager.application.tpvpos.ui.onfinishpurchase.components.FinishPurchaseAndSendInvoiceButton
import fames.systems.bizmanager.application.tpvpos.ui.onfinishpurchase.components.FinishPurchaseButton
import fames.systems.bizmanager.application.tpvpos.ui.onfinishpurchase.components.LabelsLineOfProducts
import fames.systems.bizmanager.application.tpvpos.ui.onfinishpurchase.components.LineOfProduct
import fames.systems.bizmanager.application.tpvpos.ui.onfinishpurchase.components.MoneyForReturnTextField
import fames.systems.bizmanager.application.tpvpos.ui.onfinishpurchase.components.MoneyReceivedTextField
import fames.systems.bizmanager.application.tpvpos.ui.onfinishpurchase.components.TotalPriceTextField
import fames.systems.bizmanager.domain.models.Purchase
import fames.systems.bizmanager.domain.models.UiState
import fames.systems.bizmanager.infrastructure.resources.buttonColor
import fames.systems.bizmanager.infrastructure.resources.disabledButtonColor
import fames.systems.bizmanager.infrastructure.utils.BoldText
import fames.systems.bizmanager.infrastructure.utils.Formats
import fames.systems.bizmanager.infrastructure.utils.RegularText
import fames.systems.bizmanager.infrastructure.utils.ScreenTitleWithBackButton
import fames.systems.bizmanager.infrastructure.utils.ShowLoadingScreen

@Composable
fun PurchaseInvoicerScreen(viewModel: PurchaseInvoicerViewModel, navController: NavHostController) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        UiState.IDLE -> ShowPurchaseInvoicerScreen(viewModel, navController)
        UiState.LOADING -> {
            ShowPurchaseInvoicerScreen(viewModel, navController)
            ShowLoadingScreen()
        }

        UiState.ERROR -> AlertDialogError(
            icon = Icons.Default.Warning,
            title = "Error",
            body = "No se ha podido realizar la venta",
            onConfirmation = { viewModel.finishPurchase() }
        )

        UiState.SUCCESS -> {
            AlertDialogError(
                icon = Icons.Default.Check,
                title = "Éxito",
                body = "Venta realizada correctamente",
                onConfirmation = {
                    viewModel.finishPurchase()
                    navController.popBackStack()
                }
            )
        }
    }

}

@Composable
fun ShowPurchaseInvoicerScreen(
    viewModel: PurchaseInvoicerViewModel,
    navController: NavHostController
) {
    val purchase by viewModel.purchase.observeAsState()
    val client by viewModel.client.observeAsState()
    viewModel.getPurchase()
    viewModel.getClient()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            ScreenTitleWithBackButton(title = "Resumen de compra", navController = navController)
            Card(
                modifier = Modifier.padding(15.dp, 15.dp, 15.dp, 20.dp),
                shape = MaterialTheme.shapes.small,
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                border = BorderStroke(1.dp, buttonColor)
            ) {
                LabelsLineOfProducts()
                purchase?.products?.forEach {
                    LineOfProduct(it)
                }

                if (purchase != null && client != null) {
                    var received by rememberSaveable { mutableDoubleStateOf(purchase?.totalPrice!!) }

                    TotalPriceTextField(totalPrice = purchase?.totalPrice!!)
                    MoneyReceivedTextField(received) { received = it }
                    MoneyForReturnTextField(Formats.price(received - purchase?.totalPrice!!))

                    ClientInfoStamp(client!!)
                    DateTimeStamp(purchase!!)
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
            FinishPurchaseButton(viewModel)
            FinishPurchaseAndSendInvoiceButton(viewModel, client?.id != 1)
        }
    }
}


