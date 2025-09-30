package com.example.project.view.counter

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.vm.counter.CounterVM

@Composable
fun CounterUI(viewModel: CounterVM) {

    val count = viewModel.counter.collectAsState()
    val context = LocalContext.current

    Column(modifier = Modifier.padding(20.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {

            Box(modifier = Modifier.fillMaxWidth())
            {
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                Text(
                    text = "${count.value}",
                    fontSize = 12.sp,
                    color = Color.Black
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Button(
                        onClick = {
                            viewModel.increaseCount()
                        }
                    ) {
                        Text(
                            text = "+",
                            fontSize = 24.sp,
                        )
                    }
                    Button(
                        onClick = {
                            if(count.value > 0){
                            viewModel.decrementCount()
                            }else{
                                Toast.makeText(context,"Cannot go below 0", Toast.LENGTH_SHORT).show()
                            }
                        }
                    ) {
                        Text(
                            text = "-",
                            fontSize = 24.sp,
                        )
                    }
                }
            }
        }
    }
}