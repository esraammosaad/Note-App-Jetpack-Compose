package com.example.noteapp_jetpack_compose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.screen.Screen
import com.example.noteapp_jetpack_compose.components.CustomBottomSheet
import com.example.noteapp_jetpack_compose.components.CustomFloatingActionButton
import com.example.noteapp_jetpack_compose.components.CustomNoteCard
import com.example.noteapp_jetpack_compose.components.CustomTopAppBar

class AllNotesScreen(private val noteViewModel: NoteViewModel) : Screen {
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        val showBottomSheet = remember { mutableStateOf(false) }
        val isEditBottomSheet = remember { mutableStateOf(false) }
        val noteTitle = remember { mutableStateOf("") }
        val noteDescription = remember { mutableStateOf("") }
        val selectedColor = remember { mutableIntStateOf(0) }
        val colorLongList = remember {
            mutableStateListOf(
                0xf21b1d1c,  // Original Color
                0xFFFF6F61,  // Coral (lighter version)
                0xFFFFB74D,  // Light Orange
                0xFFFFE0B2,  // Light Moccasin
                0xFFFFF59C,  // Light Gold
                0xFFFFA07A,  // Light Salmon (lighter version)
                0xFFFF8A65,  // Light Tomato
                0xFFFFE3B3,  // Light Peach Puff
                0xFFFFC1E3,  // Light Pink
                0xFFFFE1B2,  // Light Bisque
                0xFFFFC0CB,  // Pink
                0xFFFFF0F5,  // Lavender Blush
                0xFFFFF0F5,  // Lavender Blush (duplicate for emphasis)
                0xFFFFC1E3   // Light Pink (duplicate for emphasis)
            )
        }
        var showDialog = remember { mutableStateOf(false) }

        val note = remember {
            mutableStateOf(Note(color = 0xfffffff, isFavorite = false, title = "", description = "", createdDate = "", createdTime = ""))
        }
        val allNotes by noteViewModel.allNotes.collectAsState()

        Scaffold(
            containerColor = Color.Black,
            topBar = { CustomTopAppBar(noteViewModel) },
            floatingActionButton = { CustomFloatingActionButton(showBottomSheet) }
        ) { innerPadding ->
            if (showBottomSheet.value) {
                CustomBottomSheet(
                    note,
                    showBottomSheet,
                    noteTitle,
                    noteDescription,
                    selectedColor,
                    scope,
                    colorLongList,
                    isEditBottomSheet,
                    noteViewModel
                )
            }
            LazyColumn(

                modifier = Modifier
                    .padding(innerPadding)
                    .background(color = Color.Black)
                    .fillMaxSize(),
            ) {
                items(
                    items = allNotes?.sortedByDescending { it.createdTime } ?: emptyList(), // Sort notes to have new ones at the top
                    key = { it.id } // Assuming Note has a unique 'id' property
                ) { currentNote ->
                    CustomNoteCard(
                        context = LocalContext.current,
                        editingNote = note,
                        note = currentNote,
                        showBottomSheet = showBottomSheet,
                        noteTitle = noteTitle,
                        noteDescription = noteDescription,
                        selectedColor = selectedColor,
                        colorLongList = colorLongList,
                        isEditBottomSheet = isEditBottomSheet,
                        noteViewModel = noteViewModel ,

                        )
                }
            }
        }
    }
}
