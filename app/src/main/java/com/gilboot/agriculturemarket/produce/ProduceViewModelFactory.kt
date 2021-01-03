package com.gilboot.agriculturemarket.produce

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gilboot.agriculturemarket.Repository

@Suppress("UNCHECKED_CAST")
class ProduceViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ProduceViewModel(repository) as T
}