package com.juanca.exploreit.data.remote

import com.juanca.exploreit.models.PlacesDto
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

object Api {
    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl("https://7zpvppaxonsljd4n3rfzhlmoh40bvryi.lambda-url.us-east-1.on.aws")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
    interface ApiInterface{

        @GET("/")
        suspend fun getInfoPlaces(): Response<PlacesDto>

    }

    fun build(): ApiInterface {
        return builder.build().create(ApiInterface::class.java)
    }
}