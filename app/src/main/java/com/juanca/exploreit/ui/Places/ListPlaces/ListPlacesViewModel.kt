package com.juanca.exploreit.ui.Places.ListPlaces

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanca.exploreit.data.local.AppDataBase
import com.juanca.exploreit.models.Places
import com.juanca.exploreit.repository.aws.awsRepository
import com.juanca.exploreit.repository.aws.awsRepositoryImp
import com.juanca.exploreit.utils.OperationResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListPlacesViewModel: ViewModel() {
    private var _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    private var _message: MutableLiveData<String> = MutableLiveData()
    val message: LiveData<String> = _message

    private var _loader: MutableLiveData<Boolean> = MutableLiveData()
    val loader: LiveData<Boolean> = _loader

    private var _places: MutableLiveData<List<Places>> = MutableLiveData()
    val places: LiveData<List<Places>> = _places

    var awsRepository: awsRepository = awsRepositoryImp()

    fun getPlaces(){
        viewModelScope.launch(Dispatchers.Main) {
            try{
                _loader.value = true
                val response = withContext(Dispatchers.IO){
                    awsRepository.getPlaces()
                }

                when(response){
                    is OperationResult.Complete -> {
                        if(response.data?.code == 200){
                            _places.value = response.data.data
                        }else{
                            _error.value = response.data?.description
                        }
                    }
                    is OperationResult.Failure -> {
                        _error.value = response.exception?.message.toString()
                    }
                }
            }catch (e: Exception){
                _error.value = e.message.toString()
            }finally {
                _loader.value = false
            }
        }
    }

    fun savePLacesBd(places: List<Places>) {
        viewModelScope.launch(Dispatchers.Main) {
            try{
                _loader.value = true
                val response = withContext(Dispatchers.IO){
                    awsRepository.savePlaces(places)
                }

                when(response){
                    is OperationResult.Complete -> {
                        response.data.let {
                            _message.value = it
                        }
                    }
                    is OperationResult.Failure -> {
                        _error.value = response.exception?.message.toString()
                    }
                }
            }catch (e: Exception){
                _error.value = e.message.toString()
            }finally {
                _loader.value = false
            }
        }
    }
}