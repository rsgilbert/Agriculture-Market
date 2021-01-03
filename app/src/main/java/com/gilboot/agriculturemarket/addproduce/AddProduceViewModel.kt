package com.gilboot.agriculturemarket.addproduce

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilboot.agriculturemarket.Event
import com.gilboot.agriculturemarket.Repository
import com.gilboot.agriculturemarket.models.Produce
import kotlinx.coroutines.launch

class AddProduceViewModel(val repository: Repository) : ViewModel() {

    val pictureUriLiveData = MutableLiveData<Uri>()
    val finishLiveData = MutableLiveData<String>()

    val isAddingLiveData = MutableLiveData<Boolean>().apply { value = false }


    // set up sending snack messages
    val snackMessageLiveData = MutableLiveData<Event<String>>()
    fun setSnackMessage(message: String) {
        snackMessageLiveData.value = Event(message)
    }
}


fun AddProduceViewModel.setPicture(picture: Uri) {
    pictureUriLiveData.value = picture
}

fun AddProduceViewModel.addProduce(
    name: String,
    price: Int,
    unit: String,
    dateOfAvailability: String
) {
    viewModelScope.launch {
        val addedProduce: Produce? = repository.addProduce(
            name,
            price,
            unit,
            dateOfAvailability,
            pictureUriLiveData.value!!.toString()
        )
        when (addedProduce) {
            null -> setSnackMessage("Failed to add produce")
            else -> finishLiveData.value = addedProduce.id
        }
    }
}