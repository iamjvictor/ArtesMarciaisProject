package com.joao.login.ui.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joao.login.R
import com.joao.login.databinding.RowUserBinding
import com.joao.login.model.UserModel
import com.joao.login.ui.adapter.UserAdapter

class UserViewHolder(private val bind: RowUserBinding) : RecyclerView.ViewHolder(bind.root){

    fun bind(user: UserModel){
        bind.textName.text = user.name
        bind.textEmail.text = user.email
    }
}