package com.joao.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.joao.login.databinding.FragmentPostBinding
import com.joao.login.ui.adapter.PostAdapter
import com.joao.login.viewmodel.PostViewModel




class FeedFragment: Fragment() {
    private lateinit var binding: FragmentPostBinding
    private lateinit var viewModel: PostViewModel
    private val adapter = PostAdapter()





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostBinding.inflate(inflater, container, false)



        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.recyclerPostList.layoutManager = LinearLayoutManager(context)
        viewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        // adapter
        binding.recyclerPostList.adapter = adapter


        viewModel.getAll()
        viewModel.posts.observe(viewLifecycleOwner, Observer { users ->
            // Aqui você recebe a lista atualizada de usuários
            // Faça o que precisar com a lista, como atualizar a UI
            // ou realizar outras operações.
            adapter.updatePosts(users)
        })



    }


}