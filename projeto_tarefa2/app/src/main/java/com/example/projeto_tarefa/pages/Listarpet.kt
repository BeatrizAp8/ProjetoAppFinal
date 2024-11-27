package com.example.projeto_tarefa.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun Listarpet(db:FirebaseFirestore, cadastros: List<DocumentSnapshot>, navController: NavHostController, innerPadding: PaddingValues){

    Column(
        modifier = Modifier.padding(innerPadding)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Listar Pets", fontSize = 25.sp)
        }

        LazyColumn {
            items(pets){
                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(10.dp)
                        .border(BorderStroke(2.dp, Color.Black ), shape = RoundedCornerShape(30))
                        .padding(10.dp)
                        .clickable {
                            navController.navigate("/Atualizarcadastro/${it.id}")
                        }
                    ,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(it.get("nomedopet").toString(), fontWeight = FontWeight.Bold)
                        Text(it.get("servico").toString())
                    }
                    Column {
                        Text(it.get("data").toString())
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = {
                navController.navigate("/Cadastrarpet")
            }) {
                Text("Cadastrar pet")
            }
        }
    }
}
