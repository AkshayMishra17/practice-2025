package com.example.project.vm.counter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class CounterVM : ViewModel(){
    private val _counter = MutableStateFlow(0)
    var counter: MutableStateFlow<Int> = _counter

    fun increaseCount(){
        counter.value++
    }

    fun decrementCount(){
        if(counter.value > 0) {
            counter.value--
        }
    }
}