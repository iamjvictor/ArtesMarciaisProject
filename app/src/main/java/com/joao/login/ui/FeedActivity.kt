package com.joao.login.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.joao.login.R
import com.joao.login.databinding.ActivityFeedBinding
import com.joao.login.infra.AppConstants

class FeedActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityFeedBinding
    private var categoryId = 1

    private val dialogFragment = CardPublishFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, FeedFragment())
            .commit()
        handleFilter(R.id.image_modalidades)


        // Eventos
        binding.buttonFrase.setOnClickListener(this)
        binding.imageModalidades.setOnClickListener(this)
        binding.imageTsunami.setOnClickListener(this)
        binding.imageFace.setOnClickListener(this)


        supportActionBar?.hide()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_frase) {
            dialogFragment.show(supportFragmentManager, "publicacao_dialog")
        } else if (view.id == R.id.image_face){
            handleFilter(view.id)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, UserFragment())
                .commit()
        } else if (view.id == R.id.image_tsunami){
            handleFilter(view.id)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, InfoFragment())
                .commit()
        } else if (view.id == R.id.image_modalidades) {
            handleFilter(view.id)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, FeedFragment())
                .commit()
        }
    }


    private fun handleFilter(id: Int) {

        binding.imageModalidades.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageTsunami.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageFace.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_modalidades -> {
                binding.imageModalidades.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = AppConstants.FILTER.MODALIDADES

            }
            R.id.image_tsunami -> {
                binding.imageTsunami.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = AppConstants.FILTER.TSUNAMI
            }
            R.id.image_face -> {
                binding.imageFace.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = AppConstants.FILTER.FACE

            }
        }
    }


}


