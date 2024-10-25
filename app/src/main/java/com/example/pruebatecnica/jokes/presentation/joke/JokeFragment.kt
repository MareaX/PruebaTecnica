package com.example.pruebatecnica.jokes.presentation.joke

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.pruebatecnica.databinding.FragmentJokeBinding
import com.example.pruebatecnica.jokes.presentation.JokesViewModel
import com.example.pruebatecnica.jokes.presentation.UIState
import com.example.pruebatecnica.util.Constants.ID_CATEGORY
import com.example.pruebatecnica.util.Constants.showMessage
import com.example.retrofitlib.entity.JokeResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JokeFragment : Fragment() {
    private lateinit var binding: FragmentJokeBinding
    private val viewModel by viewModels<JokesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJokeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        arguments?.let {
            viewModel.getJoke(it.getString(ID_CATEGORY) ?: "")
        }
    }

    private fun setUpObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            uiStateObserver(it)
        }
    }

    private var uiStateObserver = { uiState: UIState ->
        when (uiState) {
            is UIState.Loading -> {
            }

            is UIState.GetJokeSuccess -> {
                setView(uiState.jokeModel)
            }

            is UIState.Error -> {
                context?.let {
                    showMessage(binding.root,it, uiState.errorMessage)
                }
            }

            else -> Unit
        }
    }

    private fun setView(jokeModel: JokeResponse?) {
        jokeModel?.let {
            binding.tvId.text = it.id
            binding.tvJoke.text = it.joke
            binding.startDate.text = it.startDate
            binding.updateDate.text = it.updateDate
        }
    }
}