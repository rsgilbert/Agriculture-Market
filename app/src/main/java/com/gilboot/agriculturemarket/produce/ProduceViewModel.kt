package com.gilboot.agriculturemarket.produce

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilboot.agriculturemarket.Event
import com.gilboot.agriculturemarket.Repository
import com.gilboot.agriculturemarket.models.Produce
import kotlinx.coroutines.launch


class ProduceViewModel(val repository: Repository) : ViewModel() {
    val produceListLiveData = repository.getAllProduces()

    val currentProduceLiveData = MutableLiveData<Produce>()


    // set up sending snack messages
    val snackMessageLiveData = MutableLiveData<Event<String>>()
    fun setSnackMessage(message: String) {
        snackMessageLiveData.value = Event(message)
    }

}


fun ProduceViewModel.setCurrentProduce(produce: Produce) {
    currentProduceLiveData.value = produce
}

fun ProduceViewModel.setCurrentProduceFromId(produceId: String) {
    viewModelScope.launch {
        val produce: Produce? = repository.getOneProduce(produceId)
        currentProduceLiveData.value = produce
    }

}
