package pt.ipt.dama2026.mei_api_compose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pt.ipt.dama2026.mei_api_compose.model.Note
import pt.ipt.dama2026.mei_api_compose.model.NoteRequest
import pt.ipt.dama2026.mei_api_compose.retrofit.RetrofitInstance

class NotaViewModel : ViewModel() {

    private val _notas = MutableStateFlow<List<Note?>>(emptyList())
    val notas: StateFlow<List<Note?>> = _notas

    // Construtor
    init {
        carregarNotas()
    }

    /**
     * lê os dados das Notas da API e atualiza o StateFlow
     */
    private fun carregarNotas() {
        viewModelScope.launch {
            try {
                _notas.value = RetrofitInstance.api.obterNotas().sortedByDescending{ it.id}
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun adicionarNota(titulo: String, descricao: String, imagem: String) {
        viewModelScope.launch {
            try{
                val novaNota = RetrofitInstance.api.criarNota(
                    NoteRequest(titulo, descricao, imagem)
                )
                // Atualiza a lista de notas com a nova nota criada
                _notas.value += novaNota
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}