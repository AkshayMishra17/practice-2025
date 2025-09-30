package com.example.project.vm.name

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.models.name.Name
import com.example.project.models.name.NameResponse
import com.example.project.models.name.nameJson
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NameVM: ViewModel() {
    private val _names = MutableStateFlow<List<String>>(emptyList())
    val names: MutableStateFlow<List<String>> = _names

    fun fetchNames(){
        viewModelScope.launch {
            try {
             val gson = Gson()
                val response = gson.fromJson(nameJson, NameResponse::class.java)
                val nameList = response.data.map { it.name }

                _names.value = nameList
            }catch (e: Exception) {
                println(e.message)
            }
        }
    }
}