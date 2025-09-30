package com.example.project

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.project.nav.NavHost
import com.example.project.ui.theme.ProjectTheme
import com.example.project.view.name.NameUI
import com.example.project.vm.name.NameVM
import com.example.project.vm.product.ProductVM

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            val viewModel: CounterVM by viewModels()
//            val viewModel: TodoVM by viewModels()

//            val viewModel: ProductVM by viewModels()
            val viewModel : NameVM by viewModels()
            ProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    CatUi()
//                    CounterUI(viewModel)
//                    TodoUI(viewModel)
//                    NavHost(viewModel)
                    NameUI(viewModel)
                }
            }
        }
    }
}
