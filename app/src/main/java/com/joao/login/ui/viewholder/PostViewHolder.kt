package com.joao.login.ui.viewholder

import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joao.login.databinding.RowInfoBinding
import com.joao.login.databinding.RowPostBinding
import com.joao.login.model.InfoModel
import com.joao.login.model.PostModel

class PostViewHolder(private val bind: RowPostBinding): RecyclerView.ViewHolder(bind.root) {

    fun bind(post: PostModel) {
        bind.textTitle.text = post.title
        bind.textContent.text = post.content
        bind.textDate.text = post.date

        val imageBlob = post.imageBlob
        Log.d("PostViewHolder", "Image Byte Array: ${imageBlob?.size}")

        if (imageBlob != null) {
            val bitmap = BitmapFactory.decodeByteArray(imageBlob, 0, imageBlob.size)
            Log.d("PostViewHolder", "Bitmap is null: ${bitmap == null}")

            if (bitmap != null) {
                bind.imagePost.setImageBitmap(bitmap)
                bind.imagePost.visibility = View.VISIBLE
            } else {
                bind.imagePost.visibility = View.GONE
            }
        } else {
            bind.imagePost.visibility = View.GONE
        }




    }

}