package com.joao.login.ui.adapter

import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import com.joao.login.databinding.RowInfoBinding
import com.joao.login.databinding.RowPostBinding
import com.joao.login.model.InfoModel
import com.joao.login.model.PostModel
import com.joao.login.model.UserModel
import com.joao.login.ui.viewholder.InfoViewHolder
import com.joao.login.ui.viewholder.PostViewHolder


// PostAdapter.kt -  O adaptador que liga a lista de informações (PostModel) a cada item da RecyclerView.

class PostAdapter() : RecyclerView.Adapter<PostViewHolder>() {

    private var posts: List<PostModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val item =  RowPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(item)

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun updatePosts(list: List<PostModel>) {
        posts = list
        notifyDataSetChanged()
    }


}
