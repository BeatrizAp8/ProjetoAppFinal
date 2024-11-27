package com.example.projeto_tarefa.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.projeto_tarefa.Navigation.Valores
import com.example.projeto_tarefa.components.Field
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun Atualizarcadastro(db: FirebaseFirestore, id: String, navController: NavHostController, innerPadding: PaddingValues, valores:Valores){
    var mensagem = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Atualizar cadastro", fontSize = 25.sp)
        Column {
            Field("Nome do pet: ", valores.nomedopet)
            Field("Serviço: ", valores.servico)
            Field("Data: ", valores.data)
        }

        Text(mensagem.value, color = Color.Red)

        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Button(onClick = {
                    db.collection("lembrete").document(id).delete().addOnSuccessListener {
                        navController.navigate("/Listarpet")
                    }.addOnFailureListener {
                        mensagem.value = it.message.toString()
                    }
                }) {
                    Text("Excluir")
                }
            }
            Column {
                Button(onClick = {
                    var data = hashMapOf(
                        "Nome do pet" to valores.nomedopet.value,
                        "Serviço" to valores.servico.value,
                        "data" to valores.data.value,
                    )

                    db.collection("cadastropet").document(id).update(data as Map<String, Any>)
                        .addOnSuccessListener {
                            navController.navigate("/Listarpet")
                        }
                        .addOnFailureListener {
                            mensagem.value = it.message.toString()
                        }

                }) {
                    Text("Atualizar")
                }
            }
        }

    }
}
