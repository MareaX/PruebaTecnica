package com.example.pruebatecnica.jokes.domain.usecase

import com.example.pruebatecnica.jokes.domain.repository.JokesRepository
import com.example.pruebatecnica.util.ServiceResponse
import com.example.retrofitlib.entity.JokeResponse
import javax.inject.Inject

class JokeUseCase @Inject constructor(private val jokesRepository: JokesRepository) {

    suspend fun execute(category: String):JokeUseCaseResult{
        return when(val result = jokesRepository.getJokesByCategory(category)){
            is ServiceResponse.Successful -> JokeUseCaseResult.Success(result.content)
            is ServiceResponse.Failed -> JokeUseCaseResult.Fail(result.errorMessage)
        }
    }
}

sealed class JokeUseCaseResult {
    data class Success(val joke: JokeResponse?) : JokeUseCaseResult()
    data class Fail(val message: String) : JokeUseCaseResult()
}