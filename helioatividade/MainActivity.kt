package com.example.composenavigationapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.helioatividade.R



// ... (imports existentes)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = Color(0xFFE91E63), // rosa
                    secondary = Color(0xFFD8B4FE), // lilás
                    background = Color(0xFFFFF0F6) // rosa claro
                )
            ) {
                    AppNavigation()
                }
            }
        }

}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "screen_a") {
        composable("screen_a") {
            ScreenA(navController = navController)
        }
        composable("screen_b?message={message}") {
            val message = it.arguments?.getString("message")
            ScreenB(navController = navController, message = message)
        }
    }
}

// ... (ScreenA, ScreenB e Previews)

private fun AppCompatActivity.oncreate(savedInstanceState: Bundle?) {}

data class Message(val author: String, val body: String)

    @Composable
    fun MessageCard(msg: Message) {
        Row() {
            Image(
                painter = painterResource(R.drawable.imagem),
                contentDescription = "Imagem do perfil",
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(text = msg.author)
            }
        }
    }
@Composable
fun Contador() {

    var contador by remember { mutableStateOf(0) }

    Button(onClick = { contador++ }) {
        Text("Cliquei $contador vezes")
    }
}

@Composable
fun ScreenA(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFC1E3)) // rosa
            .padding(16.dp)
    ) {
        Text(text = "Esta é a Tela A")
        Button(onClick = { navController.navigate("screen_b?message=Ola da Tela A!") }) {
            Text("Ir para Tela B com Mensagem")
        }
        Button(onClick = { navController.navigate("screen_b") }) {
            Text("Ir para Tela B (sem Mensagem)")
        }
    }
}


@Composable
fun ScreenB(navController: NavController, message: String?) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Esta é a Tela B")
        message?.let { Text(text = "Mensagem da Tela A: $it") }
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar para Tela A")
        }
    }
}

// Pré-visualizações (opcional)
@Preview(showBackground = true)
@Composable
fun ScreenAPreview() {
    MaterialTheme {
        ScreenA(rememberNavController())
    }
}


@Preview(showBackground = true)
@Composable
fun ScreenBPreview() {
    MaterialTheme {
        ScreenB(rememberNavController(), "Olá da Tela A")
    }
}