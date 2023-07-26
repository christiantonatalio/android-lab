package com.christianto.natalio.android.lab.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.christianto.natalio.android.lab.R
import com.christianto.natalio.android.lab.data.NotesDataSource
import com.christianto.natalio.android.lab.model.Note
import com.christianto.natalio.android.lab.ui.components.NoteButton
import com.christianto.natalio.android.lab.ui.components.NoteInputText
import java.time.format.DateTimeFormatter

@Composable
fun NoteScreen(
    notes: List<Note>? = null,
    onAddNote: (Note) -> Unit = {},
    onRemoveNote: (Note) -> Unit = {}
) {
    val title = remember {
        mutableStateOf("")
    }
    val description = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.the_app_name))
            },
            actions = {
                Icon(
                    modifier = Modifier.padding(end = 8.dp),
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Notification Icon"
                )
            },
            backgroundColor = Color(0xFFDADFE3)
        )

        // Content
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                modifier = Modifier.fillMaxWidth(),
                text = title.value,
                label = "Title",
                onTextChange = {
                    title.value = it
                }
            )

            NoteInputText(
                modifier = Modifier.fillMaxWidth(),
                text = description.value,
                label = "Add a note",
                onTextChange = {
                    description.value = it
                }
            )

            NoteButton(
                modifier = Modifier.padding(top = 8.dp),
                text = "Save",
                onClick = {
                    if (title.value.isNotEmpty() && description.value.isNotEmpty()) {
                        onAddNote(Note(title = title.value, description = description.value))
                        title.value = ""
                        description.value = ""
                        Toast.makeText(context, "Note ${title.value} saved!", Toast.LENGTH_SHORT).show()
                    }
                }
            )

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                notes?.let {
                    LazyColumn {
                        items(notes) {note ->
                            NoteItem(note = note, onNoteClicked = {
                                onRemoveNote(note)
                            })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
) {
    Surface(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        elevation = 8.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 16.dp)
                .clickable { onNoteClicked(note) },
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = note.title, style = MaterialTheme.typography.subtitle2)
            Text(text = note.description, style = MaterialTheme.typography.subtitle1)
            Text(
                text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")),
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(
        notes = NotesDataSource().loadNotes()
    )
}