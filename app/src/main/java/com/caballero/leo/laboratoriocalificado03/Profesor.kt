package com.caballero.leo.laboratoriocalificado03

import com.google.gson.annotations.SerializedName

data class Profesor(
    @SerializedName("name") val nombre: String,
    @SerializedName("last_name") val apellido: String,
    @SerializedName("phone_number") val telefono: String,
    @SerializedName("email") val email: String,
    @SerializedName("image_url") val fotoUrl: String
)
