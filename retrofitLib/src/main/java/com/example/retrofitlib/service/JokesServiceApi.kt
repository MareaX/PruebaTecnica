package com.example.retrofitlib.service


import com.example.retrofitlib.config.RetrofitApi.Person.Companion.CATEGORIES_URL
import com.example.retrofitlib.config.RetrofitApi.Person.Companion.JOKES_URL
import com.example.retrofitlib.entity.JokeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JokesServiceApi {

    @GET(JOKES_URL)
    suspend fun getJokesList(
        @Query("category") category : String
    ) : Response<JokeResponse>

    @GET("$CATEGORIES_URL")
    suspend fun getCategories() : Response<List<String>>
}