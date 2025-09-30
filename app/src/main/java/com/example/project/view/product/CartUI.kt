package com.example.project.view.product

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.project.models.product.Product
import com.example.project.vm.product.ProductVM

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CartUI(viewModel : ProductVM,navController: NavController){
    val cartProducts = viewModel.cartProducts.collectAsState()

    Scaffold(
        topBar = {
            Row(modifier = Modifier.fillMaxWidth().padding(18.dp),
                ){
                IconButton(
                    onClick = {
                    navController.popBackStack()
                    },
                    modifier = Modifier.size(42.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Back Icon"
                    )
                }
                Text(
                    text = "Cart",
                    fontSize = 22.sp
                )
            }
        }
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            LazyColumn {
                items(cartProducts.value){product ->
                    CartProductUI(product,viewModel)
                }
            }
        }
    }
}

@Composable
fun CartProductUI(product: Product,viewModel: ProductVM){

    Card(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)) {

            AsyncImage(
                model = product.image,
                contentDescription = "Product Image",
                modifier = Modifier.fillMaxSize().padding(18.dp)
            )

            Text(
                text = product.price.toString(),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
            )

            Text(
                text = product.title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
            )

            Button(
                onClick = { viewModel.removeFromCart(product)},
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Remove from cart",
                    fontSize = 12.sp
                )
            }
        }
    }
}