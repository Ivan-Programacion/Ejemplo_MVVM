package com.example.ejemplo_mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.ejemplo_mvvm.ui.theme.Ejemplo_MVVMTheme
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    // Creamos una instancia de nuestro CounterViewModel
    val myViewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejemplo_MVVMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Counter (myViewModel, innerPadding)
                }
            }
        }
    }
}

@Composable
fun Counter(myViewModel: CounterViewModel, paddingValues: PaddingValues) {
    // Para poder leer los datos, necesito hacer .collectAsState.
    // Con esto accedo al StateFlow que hay en nuestro VM (El Getter).
    // Sería como realizar el by remember pero accediendo al VM
    val model by myViewModel.model.collectAsState()
    Column(
        Modifier.padding(paddingValues).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(model.counter.toString(), fontSize = 48.sp)
        Button({myViewModel.increment()}) { Text("Increment") }
        Text(if(model.timer > 0) "Timer: ${model.timer}" else "SE ACABÓ EL TIEMPO")
    }
}