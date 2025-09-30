package com.example.project.vm.todo

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class TodoVM: ViewModel() {
   var task = mutableStateOf("")

    var notes = mutableStateListOf<String>()

    fun addTask() {
        if (task.value.isNotBlank()) {
        notes.add(task.value)
    }
    }
}