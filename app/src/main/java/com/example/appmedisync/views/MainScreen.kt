
package com.example.appmedisync.views

import android.graphics.Paint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appmedisync.R
import com.example.appmedisync.components.MainButton


//@Preview
@Composable
fun mainScreen(navController: NavController) {
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
            painter = painterResource(id = R.drawable.medicopaciente),
            contentDescription = "Logo de la aplicación",
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
        )

        Text(
            text = "Elige qué tipo de usuario eres:",
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 50.dp, bottom = 30.dp)
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {navController.navigate("Registrar Paciente") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .height(120.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pacienteicono),
                        contentDescription = "Icono de paciente",
                        modifier = Modifier.size(60.dp)
                    )
                    Text(text = "PACIENTE", modifier = Modifier.padding(top = 8.dp))
                }
            }

            Button(
                onClick = {navController.navigate("Registrar Medico") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .height(120.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.medicos),
                        contentDescription = "Icono de médico",
                        modifier = Modifier.size(60.dp)
                    )
                    Text(text = "MÉDICO", modifier = Modifier.padding(top = 8.dp))
                }
            }
        }
    }
}
