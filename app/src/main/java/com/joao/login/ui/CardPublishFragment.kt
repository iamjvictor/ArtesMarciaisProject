package com.joao.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.joao.login.R
import com.joao.login.databinding.CardPublishBinding
import com.joao.login.model.PostModel
import com.joao.login.viewmodel.PostViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class CardPublishFragment : DialogFragment(), View.OnClickListener {

    private lateinit var binding: CardPublishBinding
    private lateinit var viewModel: PostViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View? {
        binding = CardPublishBinding.inflate(layoutInflater)
        binding.buttonPost.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View) {
        val title = binding.editTitulo.text.toString()
        val content = binding.editContent.text.toString()
        val currentDateTime = Calendar.getInstance().time // Captura a data e hora atuais
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()) // Formata a data e hora
        val formattedDateTime = dateFormat.format(currentDateTime) // Converte para uma string formatada

        viewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        if(v.id == R.id.button_post){
            val post = PostModel(0,0,title, formattedDateTime, content)
            viewModel.insert(post)
            binding.editTitulo.setText("")
            binding.editContent.setText("")
            dismiss()

        }
    }


}
