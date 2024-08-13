package com.example.noteapp_jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.navigator.Navigator
import com.example.noteapp_jetpack_compose.presentation.viewModel.NoteViewModel
import com.example.noteapp_jetpack_compose.presentation.views.AllNotesScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val noteViewModel: NoteViewModel = hiltViewModel()
            Navigator(screen = AllNotesScreen(noteViewModel))
        }
    }
}


