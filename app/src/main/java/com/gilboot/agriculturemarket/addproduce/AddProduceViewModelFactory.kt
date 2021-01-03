package com.gilboot.agriculturemarket.addproduce

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gilboot.agriculturemarket.Repository

@Suppress("UNCHECKED_CAST")
class AddProduceViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        AddProduceViewModel(repository) as T
}