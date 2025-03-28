package com.example.appmedisync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.appmedisync.navigation.NavManager
import com.example.appmedisync.ui.theme.AppMedisyncTheme
import com.example.appmedisync.views.loginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppMedisyncTheme {
                NavManager() // Aquí se crea y gestiona la navegación
            }
        }
    }
}

