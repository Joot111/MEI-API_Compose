package pt.ipt.dama2026.mei_api_compose.retrofit

import pt.ipt.dama2026.mei_api_compose.retrofit.service.NoteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * instância SINGLETON
 * para aceder à API
 */
object RetrofitInstance {

    private const val BASE_URL = "https://adamastor.ipt.pt/api/"

    val api: NoteService by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NoteService::class.java)
    }
}