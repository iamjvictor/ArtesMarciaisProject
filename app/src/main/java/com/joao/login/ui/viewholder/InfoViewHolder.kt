package com.joao.login.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.joao.login.databinding.RowInfoBinding
import com.joao.login.databinding.RowUserBinding
import com.joao.login.model.InfoModel
import com.joao.login.model.UserModel

class InfoViewHolder(private val bind: RowInfoBinding, private val onItemClick: (InfoModel) -> Unit) : RecyclerView.ViewHolder(bind.root) {

    fun bind(info: InfoModel) {
        bind.textLessonTitle.text = info.title
        bind.textLessonTime.text = info.time
        bind.textLessonInstructor.text = info.instructor
        bind.textLessonLocation.text = info.location

        bind.root.setOnClickListener {
            onItemClick(info)
        }
    }
}

