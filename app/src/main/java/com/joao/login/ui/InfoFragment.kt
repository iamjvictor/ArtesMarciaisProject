package com.joao.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.joao.login.R
import com.joao.login.databinding.FragmentInfoBinding
import com.joao.login.model.InfoModel
import com.joao.login.ui.adapter.InfoAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager


class InfoFragment: Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentInfoBinding
    private val dialogFragment = CardDialogFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)

        binding.recyclerLessonList.setOnClickListener(this)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = InfoAdapter(getLessons()) { clickedInfo ->
            // Aqui você lida com o clique no item da RecyclerView
            showDialogForInfo(clickedInfo)
        }


        binding.recyclerLessonList.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerLessonList.adapter = adapter
    }

    private fun showDialogForInfo(clickedInfo: InfoModel) {
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val dialogFragment = CardDialogFragment()
        val infoCard = InfoCard()

        val args = Bundle().apply {
            putString("title", clickedInfo.title)
            putString("time", clickedInfo.time)
            putString("instructor", clickedInfo.instructor)
            putString("location", clickedInfo.location)
        }


        infoCard.arguments = args

        // Exibir o fragmento
        infoCard.show(requireActivity().supportFragmentManager, "infoCardFragment")
    }

    private fun getLessons(): List<InfoModel> {
        return listOf(
            InfoModel("Boxe", "TER/QUI 11:00H - 12:00H", "Denis CICA", "PROEX"),
            InfoModel("Yoga", "SEG/QUA 16:00H - 17:00H", "Instrutor 2", "PROEX"),
            // Adicione mais aulas conforme necessário
        )
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

}


