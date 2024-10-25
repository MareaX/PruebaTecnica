package com.example.pruebatecnica.jokes.domain.usecase

import com.example.pruebatecnica.jokes.domain.repository.JokesRepository
import com.example.pruebatecnica.util.ServiceResponse
import javax.inject.Inject

class CategoryUseCase @Inject constructor(private val jokesRepository: JokesRepository) {

    suspend fun execute():CategoryUseCaseResult{
        return when(val result = jokesRepository.getCategories()){
            is ServiceResponse.Successful -> CategoryUseCaseResult.Success(result.content)
            is ServiceResponse.Failed -> CategoryUseCaseResult.Fail(result.errorMessage)
        }
    }
}

sealed class CategoryUseCaseResult {
    data class Success(val categories: List<String>) : CategoryUseCaseResult()
    data class Fail(val message: String) : CategoryUseCaseResult()
}