package com.example.project.view.product

import android.annotation.SuppressLint
import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.project.models.product.Product
import com.example.project.vm.product.ProductVM

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductListUI(viewModel: ProductVM,navController: NavController) {

    val productList = viewModel.products.collectAsState()
    val error = viewModel.error.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchProducts()
    }

    Scaffold(
        topBar = { ProductListingTopBar(navController,viewModel) },
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        modifier = Modifier.statusBarsPadding()

    ) { innerPadding ->
        if(isLoading.value){
            Column(modifier = Modifier.fillMaxSize().padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                CircularProgressIndicator(modifier = Modifier.size(32.dp))
            }
        }else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(innerPadding),
                userScrollEnabled = true
            ) {
                items(productList.value) { product ->
                    ProductCardUI(product,viewModel)
                }
            }
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ProductCardUI(product: Product,viewModel: ProductVM) {

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
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
            )
            val inCart = viewModel.cartProducts.collectAsState().value.contains(product)

            if (inCart) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { viewModel.removeFromCart(product) }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Remove one"
                        )
                    }

                    IconButton(onClick = { viewModel.addToCart(product) }) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add one"
                        )
                    }
                }
            } else {
                Button(
                    onClick = { viewModel.addToCart(product) },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Add to cart",
                        fontSize = 12.sp
                    )
                }
            }

        }
    }
}


@Composable
fun ProductListingTopBar(navController: NavController, viewModel: ProductVM) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
            .padding(horizontal = 10.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Product List",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
        )

        Box(modifier = Modifier.padding(end = 16.dp),contentAlignment = Alignment.TopEnd) {
            IconButton(onClick = { navController.navigate("cart") }) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Cart",
                    modifier = Modifier.size(28.dp)
                )
            }

            val cartItems by viewModel.cartProducts.collectAsState()
            if (cartItems.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .offset(y = ((-4).dp))
                        .align(Alignment.TopEnd)
                        .background(Color.Red, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (cartItems.size > 9) "9+" else cartItems.size.toString(),
                        fontSize = 12.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}