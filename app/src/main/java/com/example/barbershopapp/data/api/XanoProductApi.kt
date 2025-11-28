package com.example.barbershopapp.data.api

import com.example.barbershopapp.model.Product
import retrofit2.Response
import retrofit2.http.*

interface XanoProductApi {
    @GET("product")
    suspend fun getProducts(): Response<List<Product>>

    @GET("product/{product_id}")
    suspend fun getProduct(@Path("product_id") productId: Int): Response<Product>

    @POST("product")
    suspend fun createProduct(@Body product: Product): Response<Product>

    @PUT("product/{product_id}")
    suspend fun updateProduct(
        @Path("product_id") productId: Int,
        @Body product: Product
    ): Response<Product>

    @DELETE("product/{product_id}")
    suspend fun deleteProduct(@Path("product_id") productId: Int): Response<Unit>
}
