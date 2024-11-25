package com.example.projeto_tarefa.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.projeto_tarefa.components.Field
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun CadastrarLembrete(db: FirebaseFirestore, navController: NavHostController, innerPadding: PaddingValues){
    var titulo = remember { mutableStateOf("") }
    var descricao = remember { mutableStateOf("") }
    var data = remember { mutableStateOf("") }

    var message = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Cadastro de lembrete", fontSize = 25.sp)
        Spacer(modifier = Modifier.size(20.dp))
        Column {
            Field("Título: ", titulo)
            Field("Descricao: ", descricao)
            Field("Data: ", data)
        }

        Text(message.value, color = Color.Red)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                var data = hashMapOf(
                    "titulo" to titulo.value,
                    "descricao" to descricao.value,
                    "data" to data.value,
                    "userId" to Firebase.auth.currentUser?.uid.orEmpty()
                )
                db.collection("lembrete").add(data).addOnSuccessListener {
                    navController.navigate("/ListarLembrete")
                }.addOnFailureListener {
                    message.value = it.message.toString()
                }
            }) {
                Text("Cadastrar")
            }
        }

    }
}