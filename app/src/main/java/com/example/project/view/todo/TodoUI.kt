package com.example.project.view.todo

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.vm.todo.TodoVM

@Composable
fun TodoUI(viewModel: TodoVM){

    Column(modifier = Modifier.fillMaxSize().padding(10.dp).padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        )
    {
        OutlinedTextField(
            value = viewModel.task.value,
            onValueChange = {viewModel.task.value = it},
            label = { Text("Enter you task") },
        )

        Spacer(modifier = Modifier.height(18.dp))

        Row(modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
            ){
            IconButton(
                onClick = {
                    viewModel.addTask()
                    viewModel.task.value = ""
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
            IconButton(
                onClick = {
                    viewModel.task.value = ""
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Add"
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth().height(1.dp),
            thickness = DividerDefaults.Thickness,
            color = DividerDefaults.color
        )

        if(viewModel.notes.isNotEmpty()){
            TaskUI(viewModel)
        }
    }


}

@Composable
fun TaskUI(viewModel: TodoVM){
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(20.dp)
    ){
        items(viewModel.notes){note ->
            Spacer(modifier = Modifier.height(5.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 0.5.dp,
                        shape = RectangleShape,
                        color = Color.Black
                    )
                    .padding(vertical = 10.dp)
            ){
               Row(modifier = Modifier.fillMaxWidth(),
                   horizontalArrangement = Arrangement.SpaceBetween
                   ){
                   Text(
                       text = note,
                       fontSize = 14.sp,
                       modifier = Modifier.padding(4.dp)
                   )
                   IconButton(onClick = {
                       viewModel.notes.remove(note)
                   }) {
                       Icon(
                           imageVector = Icons.Default.Delete,
                           contentDescription = "Delete"
                       )
                   }
               }
            }
        }
    }
}
