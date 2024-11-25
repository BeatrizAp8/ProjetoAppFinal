package com.example.projeto_tarefa.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.projeto_tarefa.components.Field
import com.example.projeto_tarefa.components.PasswordField
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Login(auth: FirebaseAuth, navController: NavHostController, innerPadding: PaddingValues){

    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var mensagem = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = {
                navController.navigate("/Cadastrar")
            }) {
                Text("Cadastrar-se", fontSize = 20.sp)
            }
        }
        Text("Login", fontSize = 35.sp)
        Column {
            Field("Email: ", email)
            PasswordField("Password: ", password)
        }
        Text(mensagem.value, color = Color.Red)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                auth.signInWithEmailAndPassword(email.value, password.value).addOnSuccessListener {
                    navController.navigate("/ListarLembrete")
                }.addOnFailureListener{
                    mensagem.value = it.message.toString()
                }
            }) {
                Text("Login", fontSize = 25.sp)
            }
        }

        Spacer(modifier = Modifier.size(20.dp))

    }
}