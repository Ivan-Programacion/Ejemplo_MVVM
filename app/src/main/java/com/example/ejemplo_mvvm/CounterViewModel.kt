package com.example.ejemplo_mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// Se hereda de ViewModel con constructor
class CounterViewModel: ViewModel() {
    // Cada dato que metamos en el VM, ha de ser un MutableStateFlow
    // Los atributos deben ser privados para que sólo se manejen desde el VM
    private var _model = MutableStateFlow(Model())
    // Getter hacia fuera de nuestro counter privado
    val model = _model.asStateFlow()
    // Después de generar la instancia con el constrcutor, lo siguiente que se ejecuta es el init
    init {
        // lanzamos el timer nada más iniciar la View
        viewModelScope.launch {
            while(_model.value.timer > 0) {
                _model.update { it.copy(timer = it.timer - 1) }
                delay(1000)
            }
        }
    }
    // Funcion que cambiará el estado de counter
    fun increment(){
        // Siempre que tengamos un MutableStateFlow, tenemos que actualizar mediante .update
        // Cada dato se deberá de actualizar haciendo una copia del mismo y cambiando el campo en cuestión
        _model.update { it.copy(counter = it.counter + 1) }
    }

}