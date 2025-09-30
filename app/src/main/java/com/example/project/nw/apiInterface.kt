package com.example.project.nw

import com.example.project.models.cat.Cat
import com.example.project.models.product.Product
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {
     @Headers("x-api-key: live_2w00SkCjYduBVfyKoSC0ixdTxV3TAa8rkyt0DAgabX5SK8PYukvuyVFB91ZDrvbN")
     @GET("v1/images/search?limit=10")
     suspend fun getCatImages(): List<Cat>

     @GET("products")
     suspend fun fetchProducts(): List<Product>
}