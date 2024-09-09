package com.joao.login.model

data class PostModel(
    var POST_ID: Int,
    var USER_ID: Int,
    var title: String,
    var date: String,
    var content: String,
    var imageUri: String? = null,
    val imageBlob: ByteArray? = null,

)