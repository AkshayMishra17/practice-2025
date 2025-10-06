package com.example.project.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.project.view.product.CartUI
import com.example.project.view.product.ProductListUI
import com.example.project.vm.product.ProductVM

@Composable
fun NavHost(viewModel: ProductVM){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "productList"
    ) {
        composable(
            "productList",
            deepLinks = listOf(
                navDeepLink { uriPattern = "myapp://productList" }
            )
            ){
            ProductListUI(viewModel,navController)
        }
        composable(
            "cart",
            deepLinks = listOf(
                navDeepLink {uriPattern  = "myapp://cartList"}
            )
            ){
            CartUI(viewModel,navController)
        }
    }
}