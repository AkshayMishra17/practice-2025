package com.example.project.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.nw.ApiObject
import com.example.project.models.Cat
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