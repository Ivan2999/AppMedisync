package com.example.appmedisync.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appmedisync.R
import com.example.appmedisync.components.MainButton

//@Preview
@Composable
//fun registroMedico(){
fun registroMedico(navController: NavController){
Column (verticalArrangement = Arrangement.Top,
    modifier = Modifier
        .fillMaxSize()
        .padding(top = 50.dp, bottom = 30.dp, start = 30.dp, end = 30.dp)
){
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
        painter = painterResource(id = R.drawable.consulta), // Reemplaza con tu imagen
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
                if (newValue.all { it.isDigit() }) { // Verifica que solo sean números
                    texto = newValue
                }
            },
            label = { Text("No. Colegiado: ") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // Configura el teclado numérico
        )


        OutlinedTextField(
            value = texto,
            onValueChange = { texto = it },
            label = { Text("Especialidad: ") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
            singleLine = true
        )

//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding()
//    ) {
//        Button(
//            onClick = { println("Botón registrarPaciente presionado") },
//            modifier = Modifier
//                .weight(1f)
//                .padding(end = 2.dp),
//            shape = RectangleShape
//        ) {
//            Text("REGISTRARME")
//        }
//    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Button(
            onClick = { println("Botón registrarPaciente presionado") },
            modifier = Modifier
                .weight(1f)
                .height(48.dp),  // Altura fija para consistencia
            shape = RoundedCornerShape(8.dp),  // Esquinas redondeadas
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF20BDD7),  // Color personalizado
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.dp,
                pressedElevation = 8.dp
            )
        ) {
            Text(
                text = "REGISTRARME",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}
}