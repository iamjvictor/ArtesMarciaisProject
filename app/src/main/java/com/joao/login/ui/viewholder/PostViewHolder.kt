package com.joao.login.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.joao.login.databinding.RowInfoBinding
import com.joao.login.databinding.RowPostBinding
import com.joao.login.model.InfoModel
import com.joao.login.model.PostModel

class PostViewHolder(private val bind: RowPostBinding): RecyclerView.ViewHolder(bind.root) {

    fun bind(post: PostModel) {
        bind.textTitle.text = post.title
        bind.textContent.text = post.content
        bind.textDate.text = post.date




    }

}