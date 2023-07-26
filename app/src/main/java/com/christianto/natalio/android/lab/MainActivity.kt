package com.christianto.natalio.android.lab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.christianto.natalio.android.lab.screen.NoteScreen
import com.christianto.natalio.android.lab.screen.NoteViewModel
import com.christianto.natalio.android.lab.ui.theme.AndroidLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                val noteViewModel: NoteViewModel by viewModels()
                NotesApp(noteViewModel)
            }
        }
    }
}

@Composable
fun NotesApp(noteViewModel: NoteViewModel = viewModel()) {
    val notesList = noteViewModel.getAllNotes()

    NoteScreen(
        notes = notesList,
        onAddNote = {
            noteViewModel.addNote(it)
        },
        onRemoveNote = {
            noteViewModel.removeNote(it)
        }
    )
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    AndroidLabTheme {
        // A surface container using the 'background' color from the theme
        content()
    }
}