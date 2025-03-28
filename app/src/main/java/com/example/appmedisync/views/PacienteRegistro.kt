package com.example.appmedisync.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appmedisync.R
import com.example.apprestarios.components.MainButton

//@Preview
@Composable
//fun registroPaciente(){
fun registroPaciente(navController: NavController){
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, bottom = 30.dp, start = 30.dp, end = 30.dp)
    ) {

        // Botón de regreso en la parte superior
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            MainButton(
                name = "Regresar",
                backcolor = Color.LightGray,
                color = Color.Black,
                onClick = { navController.popBackStack() }
            )
        }

        Image(
            painter = painterResource(id = R.drawable.examen), // Reemplaza con tu imagen
            contentDescription = "Descripción de la imagen",
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
        )

        Text(
            text = "Ingresa tus datos",
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 50.dp, bottom = 30.dp)
                .fillMaxWidth()
        )


        //FORMULARIO DE REGISTRO DEL PACIENTE
        var texto by remember { mutableStateOf("") }
        OutlinedTextField(
            value = texto,
            onValueChange = { texto = it },
            label = { Text("Nombre(s): ") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = texto,
            onValueChange = { texto = it },
            label = { Text("Apellidos: ") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = texto,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) { // Verifica que solo sean números
                    texto = newValue
                }
            },
            label = { Text("Edad: ") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // Configura el teclado numérico
        )

        OutlinedTextField(
            value = texto,
            onValueChange = { newValue ->
                if (newValue.matches(Regex("^\\d{0,2}/?\\d{0,2}/?\\d{0,4}\$"))) {
                    texto = newValue
                }
            },
            label = { Text("Fecha de nacimiento (dd/mm/aaaa):") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // Teclado numérico
        )

        var genero by remember { mutableStateOf("") }

        Column {
            Text("Género:", modifier = Modifier.padding(bottom = 8.dp))

            listOf("Masculino", "Femenino", "Otro").forEach { opcion ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().clickable { genero = opcion }
                ) {
                    RadioButton(
                        selected = genero == opcion,
                        onClick = { genero = opcion }
                    )
                    Text(opcion, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }

        OutlinedTextField(
            value = texto,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) { // Verifica que solo sean números
                    texto = newValue
                }
            },
            label = { Text("Numero de Telefono Celular: ") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // Configura el teclado numérico
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding()
        ) {
            Button(
                onClick = { println("Botón registrarPaciente presionado") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 2.dp),
                shape = RectangleShape
            ) {
                Text("REGISTRARME")
            }
        }

    }
}