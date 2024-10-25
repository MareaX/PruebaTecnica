package com.example.pruebatecnica.jokes.presentation

import com.example.retrofitlib.entity.JokeResponse


sealed class UIState {
    data object Loading : UIState()
    data class Error(val errorMessage: String) : UIState()
    data class GetCategoriesSuccess(
        val categories: List<String>
    ) : UIState()

    data class GetJokeSuccess(
        val jokeModel: JokeResponse?
    ) : UIState()
}
