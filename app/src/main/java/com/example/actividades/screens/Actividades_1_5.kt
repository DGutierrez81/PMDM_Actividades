package com.example.actividades.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*
Actividad 1:
Hacer que el texto del botón muestre "Cargar perfil", pero cambie a "Cancelar"
cuando se muestre la línea de progreso... Cuando pulsemos "Cancelar" vuelve al texto por defecto.
*/
//@Preview(showBackground = true)
@Composable
fun Actividad1() {
    var showLoading by rememberSaveable { mutableStateOf(false) }
    var txtButton by rememberSaveable { mutableStateOf("Cargar perfil") }
    var padding by rememberSaveable { mutableStateOf(0) }
    Column(
        Modifier
            .padding(padding.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (showLoading) {
            txtButton = "Cancelar"
            padding = 30
            CircularProgressIndicator(
                color = Color.Red,
                strokeWidth = 10.dp
            )
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 32.dp),
                color = Color.Red,
                trackColor = Color.LightGray
            )
        }else txtButton = "Cargar perfil"

        Button(
            onClick = { showLoading = !showLoading },
            modifier = Modifier.padding(padding.dp)
        ) {
            Text(txtButton)
            //if(showLoading) Text(text = "Cancelar") else Text(text = "Cargar perfil")
        }
    }
}

/*
Actividad 2:
Modifica ahora también que se separe el botón de la línea de progreso 30 dp,
pero usando un estado... es decir, cuando no sea visible no quiero que tenga la separación
aunque no se vea.
*/


/*
Actividad 3:
- Separar los botones entre ellos 10 dp, del indicador de progreso 15 dp y centrarlos horizontalmente.
- Cuando se clique el botón Incrementar, debe añadir 0.1 a la propiedad progress y quitar 0.1
  cuando se pulse el botón Decrementar.
- Evitar que nos pasemos de los márgenes de su propiedad progressStatus (0-1)
*/
//@Preview(showBackground = true)
@Composable
fun Actividad3() {
    var valor by rememberSaveable { mutableStateOf(0f) }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(progress = valor,
            modifier = Modifier.padding(10.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)) {
            if(valor > 1f) {
                valor = 1f
            }
            if (valor < 0f){
                valor = 0f
            }
            Button(onClick = {
                valor += 0.1f
            },
                modifier = Modifier.padding(5.dp)) {
                Text(text = "Incrementar")
            }
            Button(onClick = { valor -= 0.1f},
                modifier = Modifier.padding(5.dp)) {
                Text(text = "Reducir")
            }
        }
    }
}


/*
Actividad 4:
Sitúa el TextField en el centro de la pantalla y haz que reemplace el valor de una coma por un punto
y que no deje escribir más de un punto decimal...
*/
//@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Actividad4() {
    var myVal by rememberSaveable { mutableStateOf("") }


    if(myVal.contains(",")){
        myVal = myVal.replace(',', '.')
    }
    if(myVal.count{it == '.'} > 1){
        myVal = myVal.substring(0, myVal.length - 1)
    }
    TextField(
        value = myVal,
        onValueChange = { myVal = it },
        label = { Text(text = "Importe") },
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}


/*
Actividad 5:
Haz lo mismo, pero elevando el estado a una función superior y usando un componente OutlinedTextField
al que debes añadir un padding alrededor de 15 dp y establecer colores diferentes en los bordes
cuando tenga el foco y no lo tenga.
A nivel funcional no permitas que se introduzcan caracteres que invaliden un número decimal.
*/
@Preview(showBackground = true)

@Composable

fun Actividad5() {
    var myVal by rememberSaveable { mutableStateOf("") }

    OutlinedTextField2(
        value = myVal,
        onValueChange = { myVal = it },
        label = { Text(text = "Importe")}
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextField2(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)
){
    var texto by rememberSaveable { mutableStateOf("") }

    if(texto.contains(",")){
        texto = texto.replace(',', '.')
    }
    if(texto.count{it == '.'} > 1){
        texto = texto.substring(0, texto.length - 1)
    }

    OutlinedTextField(
        value = texto,
        onValueChange = { texto = it },
        label = { Text(text = "Importe")},
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .wrapContentSize(Alignment.Center),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Red,
            unfocusedBorderColor = Color.Blue
        )
    )
}
/*
fun Actividad5() {
    var myVal by rememberSaveable { mutableStateOf("") }


    var accion = if(myVal.isNotEmpty()){
        Modifier.border(1.dp,Color.Red)
    }else{
        Modifier.border(1.dp,Color.Blue)
    }
    OutlinedTextField(
        value = cambio(myVal, ::nuevo),
        onValueChange = { myVal = it },
        label = { Text(text = "Importe")},
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .wrapContentSize(Alignment.Center)
            .then(accion)
    )
}


fun cambio(digito: String, funcion: (String) -> String): String{

    return funcion(digito)
}

fun nuevo(digito: String): String{
    var resultado = digito
    if(resultado.contains(",")){
        resultado = digito.replace(',', '.')
    }
    if(resultado.count{it == '.'} > 1){
        resultado = digito.substring(0, digito.length - 1)
    }
    return resultado
}

 */



