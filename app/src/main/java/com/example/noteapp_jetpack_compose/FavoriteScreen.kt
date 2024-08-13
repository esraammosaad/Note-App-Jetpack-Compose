package com.example.noteapp_jetpack_compose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.noteapp_jetpack_compose.components.CustomBottomSheet
import com.example.noteapp_jetpack_compose.components.CustomNoteCard


class FavoriteScreen(private val noteViewModel: NoteViewModel) :Screen{
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        val showBottomSheet = remember { mutableStateOf(false) }
        val isEditBottomSheet = remember { mutableStateOf(false) }
        val noteTitle= remember {
            mutableStateOf("")
        }
        val noteDescription= remember {
            mutableStateOf("")
        }
        val selectedColor= remember {
            mutableIntStateOf(0)
        }
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

        val navigator= LocalNavigator.currentOrThrow
        val note =  remember {
            mutableStateOf(Note(color = 0xfffffff, isFavorite = false, title = "", description = "", createdDate = "", createdTime = ""))
        }
        val allNotes = noteViewModel.allNotes.collectAsState()

        Scaffold (
            containerColor = Color.Black,
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = Color.Black,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {

                        Icon(Icons.Default.KeyboardArrowLeft, contentDescription = null, tint = Color.White, modifier = Modifier.size(28.dp).clickable {

                            navigator.pop()
                        })

                    },

                    actions = {
                        Text("Favorite Notes", color = Color.White, fontSize = 18.sp,)

                    }

                )
            },


        ){

                innerPadding ->
            if (showBottomSheet.value) {
                CustomBottomSheet(
                    note,
                    showBottomSheet,
                    noteTitle,
                    noteDescription ,
                    selectedColor ,
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
                    allNotes.value?.filter { it.isFavorite }?: emptyList()

                ){
                        currentNote ->
                    CustomNoteCard(
                        LocalContext.current,
                        note,
                        currentNote,
                        showBottomSheet,
                        noteTitle,
                        noteDescription,
                        selectedColor,
                        colorLongList,
                        isEditBottomSheet,
                        noteViewModel,
                    )
                }
            }
        }
    }
}



