package com.juanca.exploreit.ui.Places.DetailPlaces

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juanca.exploreit.models.Places

class DetailPlaceViewModel: ViewModel() {

    private var _place: MutableLiveData<Places> = MutableLiveData()
    val place: LiveData<Places> = _place

    fun setPlace(place: Places){
        _place.value = place
    }
}