package com.example.project.vm.cat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.models.cat.Cat
import com.example.project.nw.ApiObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CatVM: ViewModel(){
    private val _images = MutableStateFlow<List<Cat>>(emptyList())
    val images: MutableStateFlow<List<Cat>> = _images

    private val _error = MutableStateFlow<String?>(null)
    val error: MutableStateFlow<String?> = _error

    fun fetchCatImages(){
        viewModelScope.launch {
            try {
                val response = ApiObject.retroInst.getCatImages()
                    _images.value = response
            }catch (e: Exception){
                _error.value = e.message.toString()
            }
        }
    }
}