package com.example.appmedisync.views

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class LoginScreenViewModel: ViewModel() {
    private val  auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)

    fun signInWithEmailAndPassword(email: String, password: String, mainScreen:() -> Unit)
    = viewModelScope.launch {
    try {
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task->
                if (task.isSuccessful){
                    Log.d("Medisync", "signInWithEmailAndPassword Logueado!!")
                    mainScreen()
                }
                else{
                    Log.d("Medisync", "hola Damaris:${task.result.toString()}")
                }
            }
    }
    catch (ex: Exception){
        Log.d("AppMediSync", "signInWithEmailAndPassword: ${ex.message}")
        }
    }

    fun createUserWhitEmailAndPassword(
        email: String,
        password: String,
        home: ()-> Unit
    ){
        if (_loading.value == false){
            _loading.value = true
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        val displayName =
                            task.result.user?.email?.split("@")?.get(0)
                        createUser(displayName)
                        home()
                    }
                    else{
                        Log.d("Medisync", "createUserWhitEmailAndPassword:${task.result.toString()}")
                    }
                    _loading.value=false
                }
        }
    }
    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        val user = mutableMapOf<String,Any>()

        user["user_id"] = userId.toString()
        user["displayName"] = displayName.toString()
        FirebaseFirestore.getInstance().collection("usuarios")
            .add(user)
            .addOnSuccessListener{
                Log.d("Medisync", "Creado ${it.id}")
            }.addOnFailureListener {
                Log.d("Medisync", "Ocurrió un error inesperado!!! ${it}")
            }
}


}
