package com.example.projeto_tarefa.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Field(label: String, valor: MutableState<String>){
    Text(label, modifier =  Modifier.padding(15.dp), fontSize = 25.sp)
    TextField(value = valor.value, onValueChange = {it -> valor.value = it}, modifier = Modifier.border(width = 1.dp, color = Color.Black))
}
