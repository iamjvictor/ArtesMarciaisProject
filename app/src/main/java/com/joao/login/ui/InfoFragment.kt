package com.joao.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.joao.login.databinding.FragmentInfoBinding
import com.joao.login.model.InfoModel
import com.joao.login.ui.adapter.InfoAdapter


class InfoFragment: Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentInfoBinding
    private val dialogFragment = CardPublishFragment()

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
        val lessons: List<InfoModel> = getLessons()
        val infoAdapter = InfoAdapter(lessons) { info ->
            // Implementação do onItemClick
            val alertDialogBuilder = AlertDialog.Builder(requireContext())
            alertDialogBuilder.apply {
                setTitle(info.title)
                setMessage("Outras informações aqui, se necessário")
                // Adicione outras configurações do AlertDialog conforme necessário
                setPositiveButton("OK") { dialog, _ ->
                    // Implemente o que deseja fazer ao clicar no botão "OK" do AlertDialog
                    dialog.dismiss() // Fecha o AlertDialog ao clicar em "OK"
                }
                setNegativeButton("Cancelar") { dialog, _ ->
                    // Implemente o que deseja fazer ao clicar no botão "Cancelar" do AlertDialog
                    dialog.dismiss() // Fecha o AlertDialog ao clicar em "Cancelar"
                }
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }




        binding.recyclerLessonList.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerLessonList.adapter = infoAdapter
    }

    private fun showDialogForInfo(clickedInfo: InfoModel) {
        val fragmentManager = requireActivity().supportFragmentManager
        val infoCard = InfoCard()

        val args = Bundle().apply {
            putString("title", clickedInfo.title)
            putString("time", clickedInfo.time)
            putString("time2", clickedInfo.time2)
            putString("time3", clickedInfo.time3)
            putString("instructor", clickedInfo.instructor)
            putString("location", clickedInfo.location)
        }
        infoCard.arguments = args
        // Exibir o fragmento
        infoCard.show(requireActivity().supportFragmentManager, "infoCardFragment")
    }

    private fun getLessons(): List<InfoModel> {
        return listOf(
            InfoModel("Boxe", "TER/QUI 11H-12H", "Denis Cerqueira", "PROEX", "SEG/QUA 12H-14H", ),
            InfoModel("Capoeira", "TER/QUI 19H-21H", "Mestre Peixinho", "Proex", "SAB 13H-15H","SEG/QUA/SEX 18H-20H"),
            InfoModel("Defesa Pessoal", "SEG/QUA 18H-19H", "Cesár Pinudo", "Proex", "TER/QUI 9H-11H"),
            InfoModel("Funcional para artes marciais", "SEG/QUA 14H-16H", "Humberto Sant'Anna", "PROEX", "SEX 12H-13H"),
            InfoModel("Karatê", "TER/QUI/SEX 13H-14H / 16H-17H", "Tetsuo Mizuno", "PROEX"),
            InfoModel("Tai Chin Chuan", "TER/QUI 12H-13H / 17H-18H / 18H - 19H", "Alexandre Gomes", "PROEX"),
            InfoModel("Judô", "SEG/QUA/SEX 19H-21h", "Manoel Leandro", "PROEX"),
            InfoModel("Taekwondo", "SEG/SEX 9H-12H", "Fabricio Freire", "Proex"),
            InfoModel("Pilates", "TER/QUI 15H-16H", "Maíra Quitete", "Proex", "QUE 10-12H", "SAB 9H-11H"),
            InfoModel("Prevensão de lesões", "TER 21H-22:30", "Felipe Santana", "Proex","SEX 17H-19H / 21H-22:30", "SAB 7H-9H"),
            InfoModel("Kung Fu", "TER/QUI/SEX 6H-8H", "Pedro Duncan", "Proex"),
            InfoModel("Jiu-Jítsu", "SEG/QUA/QUI 21H-23H", "Jimmy Gusmão", "Proex"),





            )
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

}


