package com.joao.login.ui

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.joao.login.R
import com.joao.login.databinding.CardBinding
import com.joao.login.databinding.CardInfoBinding
import com.joao.login.model.PostModel
import com.joao.login.viewmodel.PostViewModel



class CardDialogFragment : DialogFragment(), View.OnClickListener {
    private lateinit var binding: CardBinding
    private lateinit var viewModel: PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View? {
        binding = CardBinding.inflate(layoutInflater)
        binding.buttonPost.setOnClickListener(this)




        return binding.root
    }

    override fun onClick(v: View) {
        val title = binding.editTitulo.text.toString()
        val content = binding.editContent.text.toString()

        viewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        if(v.id == R.id.button_post){
            val post = PostModel(0,0,title, "10/10/2023", content)
            viewModel.insert(post)

        }
    }
}
