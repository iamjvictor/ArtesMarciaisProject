package com.joao.login.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.joao.login.R
import com.joao.login.databinding.ActivityLoginBinding
import com.joao.login.viewmodel.UserViewModel


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: UserViewModel
    private val cardAbout = CardAbout()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.buttonLogin.setOnClickListener(this)
        binding.buttonSignup.setOnClickListener(this)
        binding.imageSupport.setOnClickListener(this)

        supportActionBar?.hide()

    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_login) {
            getLogged()
        } else if (v.id == R.id.button_signup) {
            startActivity(Intent(this, SignupActivity::class.java))
        } else if (v.id == R.id.image_support) {
            cardAbout.show(supportFragmentManager, "about_dialog")
        }
    }

    private fun getLogged() {
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()

        if (email != "" && password != "" && viewModel.loginUser(email, password)) {
            startActivity( Intent(this, FeedActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, R.string.validation_login, Toast.LENGTH_SHORT).show()
        }
    }
}