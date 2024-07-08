package com.joao.login.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.joao.login.R

import com.joao.login.databinding.ActivitySignupBinding
import android.util.Patterns
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import androidx.lifecycle.ViewModelProvider
import com.joao.login.model.ProfModel
import com.joao.login.model.UserModel
import com.joao.login.viewmodel.SignupViewModel

class SignupActivity : AppCompatActivity(), View.OnClickListener,
    RadioGroup.OnCheckedChangeListener {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: SignupViewModel
    private lateinit var selectedItem: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        binding.buttonSignup.setOnClickListener(this)
        binding.radioAluno.isChecked = true
        binding.radioGroup.setOnCheckedChangeListener(this)


        val data = listOf("Boxe", "Yoga", "Kickboxing")


        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Recupere a seleção atual
                 selectedItem = parent?.getItemAtPosition(position).toString()

                // Faça algo com o item selecionado

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Tratamento quando nada é selecionado
            }
        }


        // Crie um adaptador
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, data)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter



        supportActionBar?.hide()
    }

    override fun onClick(view: View) {
        val name = binding.editName.text.toString()
        val email = binding.editEmail.text.toString()
        val email2 = binding.editEmail2.text.toString()
        val password = binding.editPassword.text.toString()
        val password2 = binding.editPassword2.text.toString()
        val area = binding.radioAluno.isChecked
        val modalidade = selectedItem
        val matricula = binding.editRegister.text.toString()


        if (view.id == R.id.button_signup) {
            checkEmail(email, email2)
            checkPassword(password, password2)
            val register = if (area) "Aluno" else "Professor"
            if (area){
                if (isEmailValid(email) && isEmailValid(email2) && email == email2 && isPasswordValid(password) && isPasswordValid(password2) && password == password2) {
                    val model = UserModel(0, name, email, password, modalidade, register)
                    viewModel.insert(model)
                    finish()
                }
            } else {
                if (isEmailValid(email) && isEmailValid(email2) && email == email2 && isPasswordValid(password) && isPasswordValid(password2) && password == password2) {
                    val model = ProfModel(0, name, email, password, modalidade, register, matricula)
                    viewModel.insertProf(model)
                    finish()
                }
            }


        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        val profcheck = binding.radioProfessor.isChecked
        if (profcheck) {
            binding.editRegister.visibility = View.VISIBLE

        } else {
            binding.editRegister.visibility = View.GONE
        }
    }


    private fun checkEmail(email: String, email2: String) {
        if (isEmailValid(email) && isEmailValid(email2) && email == email2) {
            binding.editEmail.setBackgroundColor(ContextCompat.getColor(this, R.color.correct_green))
            binding.editEmail2.setBackgroundColor(ContextCompat.getColor(this, R.color.correct_green))

        } else {
            binding.editEmail.setBackgroundColor(ContextCompat.getColor(this, R.color.incorrect_red))
            binding.editEmail2.setBackgroundColor(ContextCompat.getColor(this, R.color.incorrect_red))
            Toast.makeText(this, R.string.incorrect_email, Toast.LENGTH_SHORT).show()

        }
    }

    // Confere a senha
    private fun checkPassword(password: String, password2: String){

        if (isPasswordValid(password) && isPasswordValid(password2) && password == password2) {
            binding.editPassword.setBackgroundColor(ContextCompat.getColor(this, R.color.correct_green))
            binding.editPassword2.setBackgroundColor(ContextCompat.getColor(this, R.color.correct_green))

        } else {
            binding.editPassword.setBackgroundColor(ContextCompat.getColor(this, R.color.incorrect_red))
            binding.editPassword2.setBackgroundColor(ContextCompat.getColor(this, R.color.incorrect_red))
            Toast.makeText(this, R.string.incorrect_password, Toast.LENGTH_SHORT).show()

        }

    }


    // Confere os Emails
    private fun isEmailValid(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
    // Confere a senha
    private fun isPasswordValid(password: String): Boolean {
        val pattern = "^(?=.*[0-9])(?=.*[!@#\$%^&*])[a-zA-Z0-9!@#\$%^&*]{8,}$".toRegex()
        return pattern.matches(password)
    }




}




