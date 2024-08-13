package com.example.noteapp_jetpack_compose.presentation.views.components

import android.icu.text.DateFormat
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteapp_jetpack_compose.Note
import com.example.noteapp_jetpack_compose.presentation.viewModel.NoteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Calendar

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CustomBottomSheet(
    note: MutableState<Note>,
    showBottomSheet: MutableState<Boolean>,
    noteTitle: MutableState<String>,
    noteDescription: MutableState<String>,
    selectedColor: MutableState<Int>,
    scope: CoroutineScope,
    colorLongList: SnapshotStateList<Long>,
    isEditBottomSheet: MutableState<Boolean>,
    noteViewModel: NoteViewModel

) {


    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        contentWindowInsets = { WindowInsets.ime },
        containerColor = Color(0xff1b1d1c),

        dragHandle = { BottomSheetDefaults.DragHandle() },
        onDismissRequest = {
            showBottomSheet.value = false
        },
        sheetState = sheetState
    ) {
        CustomTextField(
            text = "Note Title",
            textFieldValue = noteTitle.value,
            onValueChanged = {
                noteTitle.value = it
            },
            maxLines = 1,
            height = 50.dp
        )
        CustomTextField(
            text = "Note Description",
            textFieldValue = noteDescription.value,
            onValueChanged = {
                noteDescription.value = it
            },
            maxLines = 10,
            height = 120.dp
        )



        LazyRow(
            contentPadding = PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(colorLongList.size) { index ->
                Box(modifier = Modifier.clickable {

                    selectedColor.value = index
                }) {
                    CircularColorAvatar(color = Color(colorLongList[index]))
                    if (selectedColor.value == index) Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier.align(alignment = Alignment.Center)
                    )


                }
            }
        }



        Button(
            onClick = {
                val calender= Calendar.getInstance().time

                val dateFormat= DateFormat.getDateInstance(DateFormat.FULL).format(calender)

                val timeFormat= DateFormat.getTimeInstance(DateFormat.SHORT).format(calender)

                if(isEditBottomSheet.value) {
                    println(note.value)
                    noteViewModel.updateNote(note.value.copy(title = noteTitle.value, description = noteDescription.value, color = colorLongList[selectedColor.value]))
                    isEditBottomSheet.value=false
                }else noteViewModel.insertNote(
                    Note(
                        createdDate = dateFormat,
                        createdTime = timeFormat,
                        title = noteTitle.value,
                        description = noteDescription.value,
                        color = colorLongList[selectedColor.value],
                        isFavorite = false,
                    )
                )
                noteTitle.value = ""
                noteDescription.value = ""
                selectedColor.value = 0

                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        showBottomSheet.value = false
                    }
                }
            },
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xfffe6902))
        ) {
            if(isEditBottomSheet.value) Text("Confirm Editing The Note ", fontSize = 20.sp) else Text("Add The Note",fontSize = 20.sp)
        }

        Spacer(Modifier.height(40.dp))



    }
}