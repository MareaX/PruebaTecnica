package com.example.pruebatecnica.jokes.presentation.category

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebatecnica.databinding.ItemCategoryBinding


class CategoryAdapter(val clickAction: (item: String) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var categoryList: MutableList<String> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(inflater, parent, false)
        return CategoryListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        categoryList[position].let { item ->
            (holder as CategoryListViewHolder).bind(item)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<String>) {
        this.categoryList.clear()
        this.categoryList.addAll(data)
        notifyDataSetChanged()
    }

    inner class CategoryListViewHolder(
        private val binding: ItemCategoryBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.tvCategoryName.text = item
            binding.clItemCategory.setOnClickListener {
                clickAction(item)
            }
        }
    }
}