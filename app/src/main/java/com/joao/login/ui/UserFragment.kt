package com.joao.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.joao.login.databinding.ActivityUserBinding
import com.joao.login.ui.adapter.UserAdapter
import com.joao.login.viewmodel.UserViewModel

class UserFragment : Fragment() {

    private var _binding: ActivityUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserViewModel
    private val adapter = UserAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // layout
        binding.recyclerAllUsers.layoutManager = LinearLayoutManager(context)

        // adapter
        binding.recyclerAllUsers.adapter = adapter

        viewModel.getAll()
        viewModel.users.observe(viewLifecycleOwner, Observer { users ->
            // Aqui você recebe a lista atualizada de usuários
            // Faça o que precisar com a lista, como atualizar a UI
            // ou realizar outras operações.
            adapter.updateUsers(users)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
