package pt.ipt.dama2026.mei_api_compose.model

import com.google.gson.annotations.SerializedName

data class NoteRequest(
    @SerializedName(value = "title") val title: String,
    @SerializedName(value = "description") val description: String,
    @SerializedName(value = "image") val image: String
)
