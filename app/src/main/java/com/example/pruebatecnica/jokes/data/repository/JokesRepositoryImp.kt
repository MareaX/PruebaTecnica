package com.example.pruebatecnica.jokes.data.repository

import com.example.pruebatecnica.jokes.domain.repository.JokesRepository
import com.example.pruebatecnica.util.ServiceResponse
import com.example.retrofitlib.entity.JokeResponse
import com.example.retrofitlib.service.JokesServiceApi
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class JokesRepositoryImp @Inject constructor(private val jokesServiceApi: JokesServiceApi) :
    JokesRepository {
    override suspend fun getCategories(): ServiceResponse<List<String>> =
        withContext(Dispatchers.IO) {
            try {
                val response = jokesServiceApi.getCategories()
                return@withContext if(response.isSuccessful){
                    ServiceResponse.Successful(response.body() ?: emptyList())
                }else{
                    ServiceResponse.Failed("Intente mas tarde")
                }
            } catch (e: Exception) {
                ServiceResponse.Failed("Intente mas tarde")
            }
        }

    override suspend fun getJokesByCategory(category: String): ServiceResponse<JokeResponse?> =
        withContext(Dispatchers.IO) {
            try {
                val response = jokesServiceApi.getJokesList(category)
                return@withContext if(response.isSuccessful){
                    response.body()?.let {
                        ServiceResponse.Successful(it)
                    } ?: run {
                        ServiceResponse.Failed("Intente mas tarde")
                    }
                }else{
                    ServiceResponse.Failed("Intente mas tarde")
                }
            } catch (e: Exception) {
                ServiceResponse.Failed("Intente mas tarde")
            }
        }
}