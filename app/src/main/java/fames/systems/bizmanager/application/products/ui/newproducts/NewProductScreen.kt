@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package fames.systems.bizmanager.application.products.ui.newproducts

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import fames.systems.bizmanager.R
import fames.systems.bizmanager.application.products.domain.models.SubProduct
import fames.systems.bizmanager.application.products.ui.newproducts.components.ImageSelectorButton
import fames.systems.bizmanager.application.products.ui.newproducts.components.OnSaveProductButton
import fames.systems.bizmanager.application.products.ui.newproducts.components.PreviewSelectedImage
import fames.systems.bizmanager.domain.models.UiState
import fames.systems.bizmanager.infrastructure.resources.buttonColor
import fames.systems.bizmanager.infrastructure.resources.disabledButtonColor
import fames.systems.bizmanager.infrastructure.utils.dialogs.BasicAlertDialog
import fames.systems.bizmanager.infrastructure.utils.sharedcomponents.HorizontalLine
import fames.systems.bizmanager.infrastructure.utils.sharedcomponents.ShowLoadingView
import fames.systems.bizmanager.infrastructure.utils.sharedcomponents.TitleWithBackButton
import fames.systems.bizmanager.infrastructure.utils.values.BoldText

@Composable
fun NewProductScreen(viewModel: NewProductViewModel, navController: NavHostController) {
    val uiState: UiState by viewModel.uiState.collectAsState()

    when (uiState) {
        UiState.IDLE -> NewProductScreenContent(viewModel, navController)
        UiState.LOADING -> {
            NewProductScreenContent(viewModel = viewModel, navController = navController)
            ShowLoadingView()
        }

        UiState.ERROR -> BasicAlertDialog(
            icon = Icons.Default.Warning,
            title = "Error al añadir producto",
            body = "No hay conexión con el servidor",
            color = Color.Red,
            onConfirmation = { viewModel.finishInsert() }
        )

        UiState.SUCCESS -> {
            Toast.makeText(LocalContext.current, "Producto añadido con éxito", Toast.LENGTH_SHORT)
                .show()
            viewModel.finishInsert()
            navController.popBackStack()
        }

        UiState.PLUS -> TODO()
    }
}

@Composable
fun NewProductScreenContent(viewModel: NewProductViewModel, navController: NavHostController) {
    val insertEnable by viewModel.insertEnable.observeAsState(initial = false)
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) imageUri = uri
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TitleWithBackButton("Nuevo Producto", navController)
            PreviewSelectedImage(imageUri)
            ImageSelectorButton(galleryLauncher)
            ProductForm(viewModel)
            OnSaveProductButton(insertEnable, imageUri, viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductNameTextField(productName: String, onInsertChange: (String) -> Unit) {
    OutlinedTextField(
        value = productName,
        onValueChange = { onInsertChange(it) },
        placeholder = { Text(text = "Nombre del producto...", color = Color.Gray) },
        modifier = Modifier
            .padding(horizontal = 25.dp, vertical = 7.dp)
            .fillMaxWidth()
            .height(60.dp),
        label = { Text(text = "Nombre") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "productName_icon",
                tint = Color.Black
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            errorLabelColor = Color.Red,
            errorIndicatorColor = Color.Red,
            textColor = Color.Black,
            containerColor = Color.White,
        ),
    )
}

@Composable
fun ProductForm(viewModel: NewProductViewModel) {
    val productName by viewModel.productName.observeAsState(initial = "")
    val description by viewModel.description.observeAsState(initial = "")
    val sellPrice by viewModel.sellPrice.observeAsState(initial = 0.0)
    var quantityExpenses by rememberSaveable { mutableIntStateOf(1) }

    HorizontalLine(color = Color.DarkGray)
    Spacer(modifier = Modifier.height(12.dp))
    BoldText(text = "Datos del producto")

    ProductNameTextField(productName) { viewModel.onInsertChange(it, description, sellPrice) }
    ProductDescriptionTextField(description) { viewModel.onInsertChange(productName, it, sellPrice) }
    ProductPriceTextField(sellPrice) { viewModel.onInsertChange(productName, description, it) }

    HorizontalLine(color = Color.DarkGray)
    Spacer(modifier = Modifier.height(12.dp))
    BoldText(text = "Gastos de producción")

    repeat(quantityExpenses) {
        ProductExpenseTextLabel(viewModel, it)
    }
    NewExpenseLineButton { quantityExpenses++ }
}

@Composable
fun NewExpenseLineButton(incrementQuantityExpenses: () -> Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            enabled = true,
            onClick = { incrementQuantityExpenses() },
            shape = ShapeDefaults.Small,
            modifier = Modifier
                .height(60.dp)
                .padding(horizontal = 17.dp, vertical = 7.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black,
                disabledContainerColor = Color.White,
                disabledContentColor = Color.LightGray
            )
        ) {
            Text(text = "Añadir nuevo gasto")
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun ProductExpenseTextLabel(
    viewModel: NewProductViewModel,
    id: Int
) {
    var quantity by rememberSaveable { mutableStateOf("") }
    var quantityCurrency by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var costPrice by rememberSaveable { mutableStateOf("") }

    var ml by rememberSaveable { mutableStateOf(false) }
    var gr by rememberSaveable { mutableStateOf(false) }
    var ud by rememberSaveable { mutableStateOf(true) }

    if (ml) quantityCurrency = "ml"
    else if (gr) quantityCurrency = "gr"
    else if (ud) quantityCurrency = "ud"

    val productExpense = SubProduct(id.toString(), name, ((costPrice.ifEmpty { 0.0 }).toString().toDouble()), ((quantity.ifEmpty { 0 }).toString().toInt()), quantityCurrency)

    LazyRow(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        item {
            ExpenseQuantityTextField(quantity) {
                quantity = it
                viewModel.onExpensedProductChange(id, productExpense)
            }
            ExpenseCurrencyMl(ml) {
                quantityCurrency = "ml"
                ml = true
                gr = false
                ud = false
                viewModel.onExpensedProductChange(id, productExpense)
            }
            ExpenseCurrencyGr(gr) {
                quantityCurrency = "gr"
                ml = false
                gr = true
                ud = false
                viewModel.onExpensedProductChange(id, productExpense)
            }
            ExpenseCurrencyUd(ud) {
                quantityCurrency = "ud"
                ml = false
                gr = false
                ud = true
                viewModel.onExpensedProductChange(id, productExpense)
            }
            ExpenseNameTextField(name) {
                name = it
                viewModel.onExpensedProductChange(id, productExpense)
            }
            ExpenseCostPriceTextField(costPrice) {
                costPrice = it
                viewModel.onExpensedProductChange(id, productExpense)
            }
        }
    }
}

@Composable
fun ExpenseCostPriceTextField(costPrice: String, onCostPriceChange: (String) -> Unit) {
    OutlinedTextField(
        value = costPrice,
        onValueChange = {
            onCostPriceChange(it)
        },
        placeholder = { Text(text = "Precio...", color = Color.Gray) },
        modifier = Modifier
            .padding(horizontal = 25.dp, vertical = 7.dp)
            .width(120.dp)
            .height(60.dp),
        label = { Text(text = "Precio") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done
        ),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            errorLabelColor = Color.Red,
            errorIndicatorColor = Color.Red,
            textColor = Color.Black,
            containerColor = Color.White
        ),
    )
}

@Composable
fun ExpenseCurrencyUd(ud: Boolean, onClickButton: () -> Unit) {
    Button(
        enabled = true,
        onClick = { onClickButton() },
        shape = ShapeDefaults.Large,
        modifier = Modifier
            .height(50.dp)
            .padding(horizontal = 5.dp, vertical = 2.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (ud) buttonColor else Color.White,
            contentColor = if (ud) Color.White else buttonColor,
            disabledContainerColor = disabledButtonColor,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "ud")
    }
}

@Composable
fun ExpenseCurrencyGr(gr: Boolean, onClickButton: () -> Unit) {
    Button(
        enabled = true,
        onClick = { onClickButton() },
        shape = ShapeDefaults.Large,
        modifier = Modifier
            .height(50.dp)
            .padding(horizontal = 5.dp, vertical = 2.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (gr) buttonColor else Color.White,
            contentColor = if (gr) Color.White else buttonColor,
            disabledContainerColor = disabledButtonColor,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "gr")
    }
}

@Composable
fun ExpenseCurrencyMl(ml: Boolean, onClickButton: () -> Unit) {
    Button(
        enabled = true,
        onClick = { onClickButton() },
        shape = ShapeDefaults.Large,
        modifier = Modifier
            .height(50.dp)
            .padding(horizontal = 5.dp, vertical = 2.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (ml) buttonColor else Color.White,
            contentColor = if (ml) Color.White else buttonColor,
            disabledContainerColor = disabledButtonColor,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "ml")
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseQuantityTextField(quantity: String, onDescriptionChange: (String) -> Unit) {
    OutlinedTextField(
        value = quantity,
        onValueChange = {
            onDescriptionChange(it)
        },
        placeholder = { Text(text = "Cantidad...", color = Color.Gray) },
        modifier = Modifier
            .padding(horizontal = 25.dp, vertical = 7.dp)
            .width(70.dp)
            .height(60.dp),
        label = { Text(text = "Unds") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Next
        ),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            errorLabelColor = Color.Red,
            errorIndicatorColor = Color.Red,
            textColor = Color.Black,
            containerColor = Color.White
        ),
    )
}

@Composable
fun ExpenseNameTextField(name: String, onNameChange: (String) -> Unit) {
    OutlinedTextField(
        value = name,
        onValueChange = {
            onNameChange(it)
        },
        placeholder = { Text(text = "Nombre...", color = Color.Gray) },
        modifier = Modifier
            .padding(horizontal = 25.dp, vertical = 7.dp)
            .width(180.dp)
            .height(60.dp),
        label = { Text(text = "Nombre") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            errorLabelColor = Color.Red,
            errorIndicatorColor = Color.Red,
            textColor = Color.Black,
            containerColor = Color.White
        ),
    )
}

@Composable
fun ProductPriceTextField(sellPrice: Double, onInsertChange: (Double) -> Unit) {
    OutlinedTextField(
        value = sellPrice.toString(),
        onValueChange = { onInsertChange(it.toDouble()) },
        placeholder = { Text(text = "Precio de venta...", color = Color.Gray) },
        modifier = Modifier
            .padding(horizontal = 25.dp, vertical = 7.dp)
            .fillMaxWidth()
            .height(60.dp),
        label = { Text(text = "Precio €") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "price_icon",
                tint = Color.Black
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Next
        ),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            errorLabelColor = Color.Red,
            errorIndicatorColor = Color.Red,
            textColor = Color.Black,
            containerColor = Color.White
        ),
    )
    Spacer(modifier = Modifier.height(50.dp))
}

@Composable
fun ProductDescriptionTextField(description: String, onInsertChange: (String) -> Unit) {
    OutlinedTextField(
        value = description,
        onValueChange = { onInsertChange(it) },
        placeholder = { Text(text = "Descripción del producto...", color = Color.Gray) },
        modifier = Modifier
            .padding(horizontal = 25.dp, vertical = 7.dp)
            .fillMaxWidth()
            .height(Dp.Infinity),
        label = { Text(text = "Descripción") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "description_icon",
                tint = Color.Black
            )
        },
        singleLine = false,
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            errorLabelColor = Color.Red,
            errorIndicatorColor = Color.Red,
            textColor = Color.Black,
            containerColor = Color.White
        ),
    )

}
