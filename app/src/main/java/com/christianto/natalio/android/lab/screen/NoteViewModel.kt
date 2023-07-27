package com.christianto.natalio.android.lab.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christianto.natalio.android.lab.model.Note
import com.christianto.natalio.android.lab.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes()
                .distinctUntilChanged()
                .collect { listOfNotes ->
                    _noteList.value = listOfNotes
                }
        }
    }

    fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }

    fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }

    fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }
}