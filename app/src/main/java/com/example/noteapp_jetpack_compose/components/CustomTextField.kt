package com.example.noteapp_jetpack_compose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(text:String, textFieldValue:String,onValueChanged: (String) -> Unit,maxLines:Int,height: Dp){



    Column(
        modifier = Modifier.padding(all = 8.dp),
    ) {
        Spacer(Modifier.height(16.dp))
        Text(
            text = text, style = TextStyle(
                fontSize = 16.sp,
                color = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(height),
            value = textFieldValue,
            onValueChange = onValueChanged,
            shape = RoundedCornerShape(20),
            maxLines = maxLines,
            textStyle = TextStyle(color = Color.White),

            colors = OutlinedTextFieldDefaults. colors(
                focusedContainerColor = Color(0xff1b1d1c),
                focusedBorderColor = Color.LightGray,
                cursorColor = Color.White,


                )


        )
    }
}