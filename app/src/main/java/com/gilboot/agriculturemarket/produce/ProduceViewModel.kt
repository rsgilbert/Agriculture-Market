package com.gilboot.agriculturemarket.produce

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gilboot.agriculturemarket.Event
import com.gilboot.agriculturemarket.Repository
import com.gilboot.agriculturemarket.models.Produce


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

