package pt.ipt.dama2026.mei_api_compose.model

import com.google.gson.annotations.SerializedName

data class Note(
    @SerializedName(value = "id") val id: Int,
    @SerializedName(value = "title") val title: String,
    @SerializedName(value = "description") val description: String,
    @SerializedName(value = "image") val image: String
)
