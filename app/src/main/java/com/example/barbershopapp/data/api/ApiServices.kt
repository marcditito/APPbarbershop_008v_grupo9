package com.example.barbershopapp.data.api

import retrofit2.Response
import retrofit2.http.*


interface ProductApiService {

    @GET("product")
    suspend fun getAllProducts(): Response<List<ProductResponse>>

    @POST("product")
    suspend fun createProduct(@Body product: CreateProductRequest): Response<ProductResponse>

    @GET("product/{product_id}")
    suspend fun getProduct(@Path("product_id") productId: Long): Response<ProductResponse>

    @PUT("product/{product_id}")
    suspend fun updateProduct(
        @Path("product_id") productId: Long,
        @Body product: UpdateProductRequest
    ): Response<ProductResponse>

    @DELETE("product/{product_id}")
    suspend fun deleteProduct(@Path("product_id") productId: Long): Response<Unit>
}
