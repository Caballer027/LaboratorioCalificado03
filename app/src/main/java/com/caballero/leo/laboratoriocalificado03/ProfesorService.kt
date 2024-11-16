package com.caballero.leo.laboratoriocalificado03

import retrofit2.http.GET
import com.google.gson.annotations.SerializedName

data class ProfesorResponse(
    @SerializedName("teachers") val teachers: List<Profesor>
)

interface ProfesorService {
    @GET("list/teacher-b")
    suspend fun getProfesores(): ProfesorResponse
}
