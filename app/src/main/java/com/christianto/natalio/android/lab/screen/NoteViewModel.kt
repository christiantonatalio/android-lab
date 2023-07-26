package com.christianto.natalio.android.lab.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.christianto.natalio.android.lab.data.NotesDataSource
import com.christianto.natalio.android.lab.model.Note

class NoteViewModel : ViewModel() {
    var noteList = mutableStateListOf<Note>()

    init {
        noteList.addAll(NotesDataSource().loadNotes())
    }

    fun addNote(note: Note) {
        noteList.add(note)
    }

    fun removeNote(note: Note) {
        noteList.remove(note)
    }

    fun getAllNotes() : List<Note> {
        return noteList
    }
}