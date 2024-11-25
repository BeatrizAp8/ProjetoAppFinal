package com.example.projeto_tarefa.Navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projeto_tarefa.pages.AtualizarLembrete
import com.example.projeto_tarefa.pages.Cadastrar
import com.example.projeto_tarefa.pages.CadastrarLembrete
import com.example.projeto_tarefa.pages.ListarLembrete
import com.example.projeto_tarefa.pages.Login
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore

@Composable
fun Navigation(innerPadding: PaddingValues){
    var navController = rememberNavController()
    var auth = Firebase.auth
    var db = Firebase.firestore
    NavHost(navController = navController, startDestination = "/"){
        composable("/") {
            Login(auth, navController, innerPadding)
        }
        composable("/Cadastrar") {
            Cadastrar(auth, navController, innerPadding)
        }
        composable("/CadastrarLembrete") {
            CadastrarLembrete(db, navController, innerPadding)
        }
        composable("/ListarLembrete") {
            var lembretes = remember { mutableStateListOf<DocumentSnapshot>() }

            db.collection("lembrete")
                .whereEqualTo("userId", Firebase.auth.currentUser?.uid.orEmpty())
                .get()
                .addOnSuccessListener {
                        it ->
                    lembretes.clear()
                    lembretes.addAll(it.toList())
                }
            ListarLembrete(db, lembretes, navController, innerPadding)
        }
        composable("/AtualizarLembrete/{id}", arguments = listOf(navArgument("id") {type = NavType.StringType})) {

            var id = it.arguments?.getString("id").orEmpty()

            var valores = remember {
                Valores()
            }

            db.collection("lembrete").document(id).get().addOnSuccessListener {
                valores.titulo.value = it.get("titulo").toString()
                valores.descricao.value = it.get("descricao").toString()
                valores.data.value = it.get("data").toString()
            }

            AtualizarLembrete(db, id, navController, innerPadding, valores)
        }
    }
}

data class Valores(
    var titulo: MutableState<String> = mutableStateOf(""),
    var descricao: MutableState<String> =  mutableStateOf(""),
    var data: MutableState<String> = mutableStateOf("")
)