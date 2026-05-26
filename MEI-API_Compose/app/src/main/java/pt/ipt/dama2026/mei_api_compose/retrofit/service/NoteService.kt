package pt.ipt.dama2026.mei_api_compose.retrofit.service

import pt.ipt.dama2026.mei_api_compose.model.Note
import pt.ipt.dama2026.mei_api_compose.model.NoteRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/*
 * classe para interagir com a API
 */
interface NoteService {

    /**
     * ler os dados de uma Nota
     */
//    @GET(value = "api/Notes/1")
//    suspend fun getNote(): Note

    /**
     * obter a lista de Notas
     */
    @GET(value = "api/Notes")
    suspend fun obterNotas(): List<Note>

    /**
     * criar uma nova Nota
     */
    @POST("api/Notes")
    suspend fun criarNota(@Body nota: NoteRequest): Note
}