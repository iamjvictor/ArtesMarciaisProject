package com.joao.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.joao.login.databinding.CardAboutBinding


class CardAbout: DialogFragment() {
    private lateinit var binding: CardAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View? {
        binding = CardAboutBinding.inflate(layoutInflater)





        return binding.root
    }
}