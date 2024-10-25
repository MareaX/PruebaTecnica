package com.example.pruebatecnica.jokes.domain.repository

import com.example.pruebatecnica.util.ServiceResponse
import com.example.retrofitlib.entity.JokeResponse

interface JokesRepository {
    suspend fun getCategories(): ServiceResponse<List<String>>
    suspend fun getJokesByCategory(category: String): ServiceResponse<JokeResponse?>

}