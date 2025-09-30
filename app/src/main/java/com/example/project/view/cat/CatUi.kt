package com.example.project.view.cat

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.project.vm.cat.CatVM

@Composable
fun CatUi(viewModel: CatVM = viewModel()) {

    val images by viewModel.images.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchCatImages()
    }

    when {
        error != null -> {
            Text(text = "Error: $error")
        }
        images.isEmpty() -> {
            Text(text = "Loading cats...")
        }
        else -> {
            LazyColumn {
                items(images) { cat ->
                    AsyncImage(
                        model = cat.url,
                        contentDescription = "Cat Image"
                    )
                }
            }
        }
    }
}

//@Composable
//fun CatUI(viewModel: CatVM){
//    val images = viewModel.images.collectAsState()
//    val error = viewModel.error.collectAsState()
//
//    LaunchedEffect(Unit) {
//        viewModel.fetchCatImages()
//    }
//
//    LazyColumn {
//       items(images.value){cat ->
//           AsyncImage(
//              model = cat.url,
//               contentDescription = "cats"
//           )
//       }
//    }
//}