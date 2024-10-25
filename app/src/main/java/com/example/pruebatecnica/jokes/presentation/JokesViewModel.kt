package com.example.pruebatecnica.jokes.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebatecnica.jokes.domain.usecase.CategoryUseCase
import com.example.pruebatecnica.jokes.domain.usecase.CategoryUseCaseResult
import com.example.pruebatecnica.jokes.domain.usecase.JokeUseCase
import com.example.pruebatecnica.jokes.domain.usecase.JokeUseCaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokesViewModel @Inject constructor(
    private val jokesUseCase: JokeUseCase,
    private val categoryUseCase: CategoryUseCase
) : ViewModel() {
    private val mutableUIState: MutableLiveData<UIState> = MutableLiveData()
    val uiState: MutableLiveData<UIState> = mutableUIState

    fun getCategories(){
        viewModelScope.launch {
            mutableUIState.postValue(UIState.Loading)
            when(val result = categoryUseCase.execute()){
                is CategoryUseCaseResult.Success -> mutableUIState.postValue(UIState.GetCategoriesSuccess(result.categories))
                is CategoryUseCaseResult.Fail -> mutableUIState.postValue(UIState.Error(result.message))
            }
        }
    }

    fun getJoke(category:String){
        viewModelScope.launch {
            mutableUIState.postValue(UIState.Loading)
            when(val result = jokesUseCase.execute(category)){
                is JokeUseCaseResult.Success -> mutableUIState.postValue(UIState.GetJokeSuccess(result.joke))
                is JokeUseCaseResult.Fail -> mutableUIState.postValue(UIState.Error(result.message))
            }
        }
    }


}