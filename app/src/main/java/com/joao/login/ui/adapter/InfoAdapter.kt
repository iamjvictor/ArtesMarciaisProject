package com.joao.login.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.joao.login.databinding.RowInfoBinding
import com.joao.login.model.InfoModel
import com.joao.login.ui.viewholder.InfoViewHolder



// InfoAdapter.kt -  O adaptador que liga a lista de informações (InfoModel) a cada item da RecyclerView.

class InfoAdapter(private val lessons: List<InfoModel>, private val onItemClick: (InfoModel) -> Unit) : RecyclerView.Adapter<InfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val item =  RowInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InfoViewHolder(item, onItemClick)

    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.bind(lessons[position])
    }

    override fun getItemCount(): Int {
        return lessons.size
    }
}

