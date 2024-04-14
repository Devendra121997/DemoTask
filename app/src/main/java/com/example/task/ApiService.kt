package com.example.task

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/posts")
    suspend fun getPosts(@Query("_page") page: Int,
                         @Query("_limit") pageSize: Int) : Response<List<DataModel>>

}

object RetrofitClient {
    fun getClient() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}