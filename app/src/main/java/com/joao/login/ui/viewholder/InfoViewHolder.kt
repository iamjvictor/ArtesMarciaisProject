package com.joao.login.ui.viewholder

import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.joao.login.databinding.RowInfoBinding
import com.joao.login.model.InfoModel

class InfoViewHolder(private val bind: RowInfoBinding, private val listener: (InfoModel) -> Unit): RecyclerView.ViewHolder(bind.root)  {

    fun bind(info: InfoModel) {
        bind.textLessonTitle.text = info.title
        bind.textLessonTime.text = info.time
        bind.textLessonInstructor.text = info.instructor
        bind.textLessonLocation.text = info.location

        bind.root.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(bind.root.context)
            alertDialogBuilder.apply {
                setTitle(info.title) // Define o título do AlertDialog como o título do objeto InfoModel
                setMessage("Mensagem de exemplo") // Mensagem opcional
                setPositiveButton("OK") { dialog, _ ->
                    // Implemente aqui o que deseja fazer ao clicar no botão "OK"
                    dialog.dismiss() // Fecha o AlertDialog
                }
                setNegativeButton("Cancelar") { dialog, _ ->
                    // Implemente aqui o que deseja fazer ao clicar no botão "Cancelar"
                    dialog.dismiss() // Fecha o AlertDialog
                }
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }





    }
}







