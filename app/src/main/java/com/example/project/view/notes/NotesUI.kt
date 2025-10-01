package com.example.project.view.notes

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.db.Notes
import com.example.project.vm.notes.NotesVM

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotesUI(viewModel: NotesVM) {
    val notes = viewModel.notes.collectAsState(initial = emptyList())
    var newNoteText by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
         Column(modifier = Modifier.fillMaxSize().padding(18.dp)){
             Row(
                 modifier = Modifier
                     .fillMaxWidth()
                     .padding(18.dp),
                 horizontalArrangement = Arrangement.Start
             ) {
                 Text(
                     text = "Notes",
                     fontSize = 22.sp,
                     fontWeight = FontWeight.SemiBold
                 )
             }
             Spacer(modifier = Modifier.height(10.dp))

             Row(
                 modifier = Modifier.fillMaxWidth(),
                 verticalAlignment = Alignment.CenterVertically
             ) {
                 OutlinedTextField(
                     value = newNoteText,
                     onValueChange = {newNoteText = it},
                     label = { Text("Enter your note") },
                     modifier = Modifier.weight(1f)
                 )

                 Spacer(modifier = Modifier.width(8.dp))

                 IconButton(
                     {
                         if (newNoteText.isNotBlank()) {
                             viewModel.addNotes(Notes(title = newNoteText, description = "", id = 0))
                             newNoteText = ""
                         }
                     },
                     modifier = Modifier.size(48.dp)
                 ) {
                     Icon(
                         imageVector = Icons.Default.Add,
                         contentDescription = "Add Note",
                         tint = MaterialTheme.colorScheme.primary
                     )
                 }
             }
         }
        },
        modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(notes.value) { note ->
                NoteUI(note)
            }
        }
    }
}

@Composable
fun NoteUI(note: Notes) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 0.5.dp,
                color = Color.Black,
                shape = RoundedCornerShape(10.dp),
            )
            .padding(12.dp)
    ) {
        Column {
            Text(
                text = note.title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = note.title,
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }
    }
}
