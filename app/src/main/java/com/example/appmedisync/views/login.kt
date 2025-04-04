package com.example.appmedisync.views

import android.util.Log
import com.example.appmedisync.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appmedisync.MainActivity


@Composable
fun loginScreen(navController: NavController,
                viewModel: LoginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    val showLoginForm= rememberSaveable {
        mutableStateOf(true)
    }
    Surface(modifier = Modifier
        .fillMaxSize()
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            if (showLoginForm.value){
                Text(text = "Iniciar sesión")
                UserForm(
                    isCreateAccount = false
                ){
                        email, password ->
                    Log.d("Medisync","Logueado con $email y $password")
                    viewModel.signInWithEmailAndPassword (email,password){
                                                //aqui va la clase, llamas la vista
                        navController.navigate(MainScreen.MainScreen.name)
                    }
                }
            }
            else{
                Text(text = "Crear una cuenta")
                UserForm(
                    isCreateAccount = true
                ){
                        email, password ->
                    Log.d("Medisync","Creando cuenta con $email y $password")
                    viewModel.createUserWhitEmailAndPassword(email, password){
                        navController.navigate(MainActivity.MainScreen.name)
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                val text1 =
                    if (showLoginForm.value)"¿No tienes cuenta?"
                    else {
                        "¿Ya tienes cuenta?"
                    }
                val text2 =
                    if (showLoginForm.value)"Regístrate"
                    else {
                        "Iniciar sesión"
                    }
                Text(text = text1)
                Text(text = text2,
                    modifier = Modifier
                        .clickable{ showLoginForm.value = !showLoginForm.value}
                        .padding(start = 5.dp),
                   // color = MaterialTheme.colors.secondaryVariant
                            )


                    }
            }
        }
    }


@Composable
fun UserForm(
    isCreateAccount:Boolean = false,
    oneDone:(String, String) -> Unit = {email, password ->}
){
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val passwordVisible = rememberSaveable {
        mutableStateOf(false)
    }
    val valido = remember (email.value, password.value){
        email.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        EmailInput(
            emailState = email
        )
        PasswordInput(
            passwordState = password,
            labelId = "Password",
            passwordVisible = passwordVisible
        )
        SubmitButton(
            textId = if (isCreateAccount)"Crear cuenta" else "Login",
            inputValido = valido
        ){
            oneDone(email.value.trim(),password.value.trim())
            keyboardController?.hide()
        }
    }
}

@Composable
fun SubmitButton(
    textId: String,
    inputValido:Boolean,
    onClick: ()->Unit
) {
    Button(onClick = onClick,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape,
        enabled = inputValido
    ) {
        Text(text = textId,
            modifier = Modifier
                .padding(5.dp)
        )
    }
}

@Composable
fun PasswordInput(
    passwordState: MutableState<String>,
    labelId: String,
    passwordVisible: MutableState<Boolean>
) {
    val visualTransformation = if (passwordVisible.value)
        VisualTransformation.None
    else PasswordVisualTransformation()
    OutlinedTextField(
        value = passwordState.value,
        onValueChange = {passwordState.value = it},
        label = {Text(text = labelId)},
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        visualTransformation = visualTransformation,
        trailingIcon = {
            if (passwordState.value.isNotBlank()){
                PasswordVisibleIcon(passwordVisible)
            }
            else null
        }
    )
}

@Composable
fun PasswordVisibleIcon(passwordVisible: MutableState<Boolean>
){
    val image =
        if (passwordVisible.value)
            Icons.Default.VisibilityOff
        else
            Icons.Default.Visibility
    IconButton(onClick = {
        passwordVisible.value = !passwordVisible.value
    }){
        Icon(
            imageVector = image,
            contentDescription = ""
        )
    }
}

@Composable
fun EmailInput(
    emailState: MutableState<String>,
    labelId:String = "Email"
){
    InputField(
        valueState = emailState,
        labelId = labelId,
        keyboardType = KeyboardType.Email
    )
}

@Composable
fun InputField(
    valueState: MutableState<String>,
    labelId: String,
    isSingleLine:Boolean = true,
    keyboardType: KeyboardType
){
    OutlinedTextField(
        value = valueState.value,
        onValueChange = {valueState.value = it},
        label = {Text(text = labelId)},
        singleLine =  isSingleLine,
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}
////@Preview
//@Composable
//fun loginScreen(navController: NavController,
//                viewModel: LoginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
//    Column(
//        verticalArrangement = Arrangement.Top,
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(top = 100.dp, bottom = 30.dp, start = 30.dp, end = 30.dp)
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.login),
//            contentDescription = "Descripción de la imagen",
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(130.dp)
//        )
//
//        Text(
//            text = "Inicia sesión o regístrate",
//            fontSize = 20.sp,
//            modifier = Modifier.padding(top = 80.dp, bottom = 30.dp)
//                .fillMaxWidth()
//        )
//
//        var email by remember { mutableStateOf("") }
//        var password by remember { mutableStateOf("") }
//
//        OutlinedTextField(
//            value = email,
//            onValueChange = { email = it },
//            label = { Text("Email") },
//            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
//            singleLine = true
//        )
//
//        OutlinedTextField(
//            value = password,
//            onValueChange = { password = it },
//            label = { Text("Password") },
//            modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
//            singleLine = true
//        )
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding()
//        ) {
//            Button(
//                onClick = { navController.navigate("mainScreen") },
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(end = 2.dp),
//                shape = RectangleShape
//            ) {
//                Text("Login")
//            }
//
//            Button(
//                onClick = {navController.navigate("mainScreen") },
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(start = 2.dp),
//                shape = RectangleShape
//            ) {
//                Text("Sign up")
//            }
//        }
//    }
//}