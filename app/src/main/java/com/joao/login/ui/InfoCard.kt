package com.joao.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.joao.login.R
import com.joao.login.databinding.CardInfoBinding
import com.joao.login.model.CardInfoModel

class InfoCard : DialogFragment() {

    private lateinit var binding: CardInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View? {
        binding = CardInfoBinding.inflate(layoutInflater)



        val args = arguments
        val title = args?.getString("title")
        showInfoCard(title)

        return binding.root
    }

    private fun showInfoCard(title: String?){
        val cardInfo = getCardInfo(title)

        binding.textLessonTitle.text = cardInfo.title
        binding.textLessonDescription.text = cardInfo.description

    }

    private fun getCardInfo(title: String?): CardInfoModel{
        if (title == "Boxe"){
            return CardInfoModel("Boxe", "Nobre arte")
        } else if (title == "Yoga"){
            return CardInfoModel("Yoga", "Alongamento")
        }
        return CardInfoModel("Padrão", "Insira aqui o texto")
    }
}