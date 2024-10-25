package com.example.pruebatecnica.jokes.presentation.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebatecnica.databinding.FragmentCategoryBinding
import com.example.pruebatecnica.jokes.presentation.JokesViewModel
import com.example.pruebatecnica.jokes.presentation.UIState
import com.example.pruebatecnica.util.Constants.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private val viewModel by viewModels<JokesViewModel>()
    private val adapter by lazy {
        CategoryAdapter {
            goToJoke(it)
        }
    }

    private fun goToJoke(it: String) {
        CategoryFragmentDirections.actionCategoryFragmentToJokeFragment(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        setUpRecyclerView()
        viewModel.getCategories()
    }

    private fun setUpRecyclerView() {
        binding.rvCategory.layoutManager = LinearLayoutManager(context)
        binding.rvCategory.adapter = adapter
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

            is UIState.GetCategoriesSuccess -> {
                adapter.setData(uiState.categories)
            }

            is UIState.Error -> {
                context?.let {
                    showMessage(binding.root,it, uiState.errorMessage)
                }
            }

            else -> Unit
        }
    }
}

/*

*/