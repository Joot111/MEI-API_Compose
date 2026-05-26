package pt.ipt.dama2026.mei_api_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import pt.ipt.dama2026.mei_api_compose.ui.theme.MEIAPI_ComposeTheme
import pt.ipt.dama2026.mei_api_compose.viewModel.NotaViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MEIAPI_ComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()
                    .padding(top = 40.dp)
                ) { innerPadding ->
                    NotaScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun NotaScreen(
    modifier: Modifier = Modifier,
    vm: NotaViewModel = viewModel()
) {
    val notas by vm.notas.collectAsState()

    // vars que vao guardar os valores dos campos do formulário
    var titulo by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var foto by remember { mutableStateOf("") }


    Column(modifier = modifier.fillMaxSize()) {
        // FORMUL�RIO
        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("titulo") }
            )
            OutlinedTextField(
                value = descricao,
                onValueChange = { descricao = it },
                label = { Text("Descri��o") }
            )
            OutlinedTextField(
                value = foto,
                onValueChange = { foto = it },
                label = { Text("URL Foto") }
            )
            Button(
                onClick = {
                    vm.adicionarNota(titulo, descricao, foto)

                    titulo = ""
                    descricao = ""
                    foto = ""
                }
            ) { Text("Adicionar Nota") }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(notas) { nota ->
                Card {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = nota?.title ?: "",
                            style = MaterialTheme.typography.headlineMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = nota?.description ?: "")

                        Spacer(modifier = Modifier.height(16.dp))

                        AsyncImage(
                            model = "https://adamastor.ipt.pt/api/imagens/" + nota?.image,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MEIAPI_ComposeTheme {
//        Greeting("Android")
//    }
//}