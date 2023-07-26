package com.christianto.natalio.android.lab.data

import com.christianto.natalio.android.lab.model.Note

class NotesDataSource {
    fun loadNotes() : List<Note> = listOf(
        Note(title = "First Note", description = "Well i guess"),
        Note(title = "Second Note", description = "Well i guess so"),
        Note(title = "Third Note", description = "Well i guess so yeah")
    )
}