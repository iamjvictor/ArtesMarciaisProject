package com.joao.login.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.joao.login.model.PostModel
import com.joao.login.model.UserModel
import com.joao.login.repository.UserRepository
import com.joao.login.ui.adapter.PostAdapter

class  PostViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository.getInstance(application)
    private val listAllPost = MutableLiveData<List< PostModel>>()
    val posts: LiveData<List<PostModel>> = listAllPost
    private val adapter = PostAdapter()

    fun insert(info: PostModel) {
        repository.insertPost(info)
        getAll()
    }

    fun getAll() {
        val postList = repository.getAllPosts()
        listAllPost.value = postList


    }
}