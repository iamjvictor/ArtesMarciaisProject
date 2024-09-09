package com.joao.login.ui

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
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
    private var imageUri: Uri? = null

    // Registrar o callback para pegar a imagem selecionada
    private val getImageFromGallery = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imageUri = it
            // Aqui você pode mostrar a imagem na interface, por exemplo, em um ImageView
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View? {
        binding = CardPublishBinding.inflate(layoutInflater)
        binding.buttonPost.setOnClickListener(this)
        binding.buttonUploadImage.setOnClickListener(this)

        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onClick(v: View) {
        val title = binding.editTitulo.text.toString()
        val content = binding.editContent.text.toString()
        val currentDateTime = Calendar.getInstance().time // Captura a data e hora atuais
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()) // Formata a data e hora
        val formattedDateTime = dateFormat.format(currentDateTime) // Converte para uma string formatada

        viewModel = ViewModelProvider(this).get(PostViewModel::class.java)
            if(v.id == R.id.button_post){
                val imageBlob = imageUri?.let { uriToByteArray(it) }
                val post = PostModel(0,0,title, formattedDateTime, content, imageUri?.toString(),imageBlob = imageBlob)
                viewModel.insert(post)
                binding.editTitulo.setText("")
                binding.editContent.setText("")
                dismiss()

            }else if (v.id == R.id.button_upload_image){
                    // Abrir a galeria para selecionar uma imagem
                    getImageFromGallery.launch("image/*")

            }
    }
    private fun uriToByteArray(uri: Uri): ByteArray? {
        val inputStream = context?.contentResolver?.openInputStream(uri)
        return inputStream?.buffered()?.use { it.readBytes() }
    }



}
