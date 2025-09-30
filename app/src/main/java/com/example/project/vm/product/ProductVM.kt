package com.example.project.vm.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.models.product.Product
import com.example.project.nw.ApiObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProductVM : ViewModel(){
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    var products: MutableStateFlow<List<Product>> = _products

    private val _error = MutableStateFlow("")
    var error : MutableStateFlow<String> = _error


    private val _isLoading = MutableStateFlow(true)
    var isLoading = _isLoading

    private val _cartProducts = MutableStateFlow<List<Product>>(emptyList())
    var cartProducts: MutableStateFlow<List<Product>> = _cartProducts

    fun fetchProducts(){
        viewModelScope.launch {
            try {
                val response = ApiObject.retroInst.fetchProducts()
                _products.value = response
            }catch (e: Exception){
                _error.value = e.message.toString()
            }finally {
                isLoading.value = false
            }
        }
    }

    fun addToCart(product: Product){
       val cartList =  _cartProducts.value.toMutableList()
        if(!cartList.contains(product)) {
            cartList.add(product)
            _cartProducts.value = cartList
        }
    }

    fun removeFromCart(product: Product){
        val cartList = _cartProducts.value.toMutableList()
        cartList.remove(product)
        _cartProducts.value = cartList
    }
}