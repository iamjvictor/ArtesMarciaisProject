package com.joao.login.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joao.login.databinding.RowUserBinding
import com.joao.login.model.UserModel
import com.joao.login.ui.viewholder.UserViewHolder

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {

    private var userList: List<UserModel> = listOf()


    override fun getItemCount(): Int {
        return userList.count()
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val item =  RowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(item)
    }

    fun updateUsers(list: List<UserModel>) {
        userList = list
        notifyDataSetChanged()
    }

}