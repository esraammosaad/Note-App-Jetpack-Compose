package com.example.noteapp_jetpack_compose.presentation.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.noteapp_jetpack_compose.presentation.views.FavoriteScreen
import com.example.noteapp_jetpack_compose.presentation.viewModel.NoteViewModel
import com.example.noteapp_jetpack_compose.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CustomTopAppBar(noteViewModel: NoteViewModel) {
    val navigator= LocalNavigator.currentOrThrow
    TopAppBar(
        colors = topAppBarColors(
            containerColor = Color.Black,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo"
            )

        },
        actions = {
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(end = 8.dp)) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite Icon",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp).clickable { navigator.push(FavoriteScreen(noteViewModel)) }
                )


            }

        }
    )
}