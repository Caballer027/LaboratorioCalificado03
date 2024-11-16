package com.caballero.leo.laboratoriocalificado03

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.caballero.leo.laboratoriocalificado03.databinding.ItemProfesorBinding

class ProfesorAdapter(
    private val profesores: List<Profesor>,
    private val onClick: (Profesor) -> Unit,
    private val onLongClick: (Profesor) -> Unit
) : RecyclerView.Adapter<ProfesorAdapter.ProfesorViewHolder>() {

    inner class ProfesorViewHolder(val binding: ItemProfesorBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfesorViewHolder {
        val binding = ItemProfesorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfesorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfesorViewHolder, position: Int) {
        val profesor = profesores[position]
        holder.binding.textViewNombre.text = profesor.nombre
        holder.binding.textViewApellido.text = profesor.apellido

        // Utilizando Glide para cargar la imagen desde la URL
        Glide.with(holder.binding.imageViewFoto.context)
            .load(profesor.fotoUrl) // URL de la imagen proporcionada por la API
            .placeholder(android.R.drawable.ic_menu_gallery) // Placeholder mientras se carga la imagen
            .into(holder.binding.imageViewFoto)

        holder.binding.root.setOnClickListener { onClick(profesor) }
        holder.binding.root.setOnLongClickListener {
            onLongClick(profesor)
            true
        }
    }

    override fun getItemCount(): Int = profesores.size
}
