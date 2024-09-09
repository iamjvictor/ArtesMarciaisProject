package com.joao.login.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.joao.login.model.ProfModel
import com.joao.login.model.UserModel
import com.joao.login.repository.UserRepository

class  SignupViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository.getInstance(application)

    fun insert(user: UserModel) {
        repository.insertUser(user)
    }
    fun insertProf(user: ProfModel) {
        repository.insertProf(user)
    }
}