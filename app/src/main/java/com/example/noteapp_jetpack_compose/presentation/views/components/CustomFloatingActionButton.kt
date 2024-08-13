package com.example.noteapp_jetpack_compose.presentation.views.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color

@Composable
fun CustomFloatingActionButton(showBottomSheet: MutableState<Boolean>) {
    FloatingActionButton(

        containerColor = Color(0xfffe6902),
        onClick = { showBottomSheet.value = true }) {
        Icon(Icons.Default.Add, contentDescription = "Add Icon", tint = Color.White)

    }
}