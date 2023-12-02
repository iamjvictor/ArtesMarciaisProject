package com.joao.login.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joao.login.model.UserModel
import com.joao.login.repository.UserRepository
import com.joao.login.ui.adapter.UserAdapter

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = UserRepository.getInstance(application.applicationContext)
    private val adapter = UserAdapter()

    private val listAllUser = MutableLiveData<List<UserModel>>()
       val users: LiveData<List<UserModel>> = listAllUser

    fun getAll() {
        val userList = repository.getAll()
       listAllUser.value = userList
        adapter.notifyDataSetChanged()
        Log.d("UserViewModel", "User list size: ${userList.size}")
    }

    fun loginUser(email: String, password: String): Boolean {
        return repository.loginUser(email, password)
    }
}