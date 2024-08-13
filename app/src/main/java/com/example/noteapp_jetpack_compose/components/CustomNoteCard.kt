package com.example.noteapp_jetpack_compose.components

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteapp_jetpack_compose.Note
import com.example.noteapp_jetpack_compose.NoteViewModel

@Composable
fun CustomNoteCard(
    context: Context,
    editingNote: MutableState<Note>,
    note: Note,
    showBottomSheet: MutableState<Boolean>,
    noteTitle: MutableState<String>,
    noteDescription: MutableState<String>,
    selectedColor: MutableState<Int>,
    colorLongList: SnapshotStateList<Long>,
    isEditBottomSheet: MutableState<Boolean>,
    noteViewModel: NoteViewModel
) {
    var showDialog by remember { mutableStateOf(false) }

    // Handle the dialog separately
    if (showDialog) {
        AlertDialog(
            iconContentColor = Color(0xfffe6902),


            containerColor = Color(0xff1b1d1c),
            textContentColor =Color(0xfffe6902) ,
            onDismissRequest = { showDialog = false },
            title = { Text("Confirm Deletion", color = Color.White) },
            text = { Text("Are you sure you want to delete this note?") },
            confirmButton = {
                TextButton(onClick = {
                    noteViewModel.deleteNote(note)
                    showDialog = false
                }, ) {
                    Text("Delete", color =Color(0xfffe6902) )
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel", color = Color(0xfffe6902))
                }
            }
        )
    }

    SwipeToDeleteContainer(
        item = note.id,
        onDelete = {                    noteViewModel.deleteNote(note)
        },
        content = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(note.color)
                )
            ) {
                Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = note.title,
                            color = Color.White,
                            fontSize = 22.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = "Edit Icon",
                                tint = Color(0xfffe6902),
                                modifier = Modifier
                                    .size(22.dp)
                                    .clickable {
                                        editingNote.value = note
                                        noteTitle.value = note.title
                                        noteDescription.value = note.description
                                        selectedColor.value = colorLongList.indexOf(note.color)
                                        isEditBottomSheet.value = true
                                        showBottomSheet.value = true
                                    }
                            )
                            Spacer(modifier = Modifier.width(5.dp))

                            Icon(
                                imageVector = if (note.isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = "Favorite Icon",
                                tint = Color(0xfffe6902),
                                modifier = Modifier
                                    .size(22.dp)
                                    .clickable {
                                        noteViewModel.updateNote(note.copy(isFavorite = !note.isFavorite))
                                    }
                            )

                            Spacer(modifier = Modifier.width(5.dp))
                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = "Delete Icon",
                                tint = Color(0xfffe6902),
                                modifier = Modifier
                                    .size(22.dp)
                                    .clickable {
                                        showDialog = true // Trigger the dialog
                                    }
                            )
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = note.description,
                        fontSize = 16.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.W500,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Box {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .align(alignment = Alignment.BottomEnd),
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Spacer(modifier = Modifier.height(8.dp))
                            HorizontalDivider(color = Color(0xfffe6902), thickness = 1.dp)
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "${note.createdTime}, ${note.createdDate}",
                                color = Color.Gray,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.W400
                            )
                        }
                    }
                }
            }
        }
    )
}
