package com.example.noteapp_jetpack_compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularColorAvatar(color: Color, size: Dp = 50.dp) {
    Box(
        modifier = Modifier
            .size(size)
            .background(color, CircleShape)
            .border(1.dp, Color.Black, CircleShape)
    )
}