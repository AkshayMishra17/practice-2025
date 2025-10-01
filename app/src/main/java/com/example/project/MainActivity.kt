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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project.db.NoteDB
import com.example.project.db.NotesRepository
import com.example.project.nav.NavHost
import com.example.project.ui.theme.ProjectTheme
import com.example.project.view.name.NameUI
import com.example.project.view.notes.NotesUI
import com.example.project.vm.name.NameVM
import com.example.project.vm.notes.NotesVM
import com.example.project.vm.notes.NotesVMFactory
import com.example.project.vm.product.ProductVM

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val db = NoteDB.getDatabase(this)
            val repo = NotesRepository(db.noteDao())

            val viewModel : NotesVM = viewModel(
                factory = NotesVMFactory(repo)
            )

            ProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NotesUI(viewModel)
                }
            }
        }
    }
}
