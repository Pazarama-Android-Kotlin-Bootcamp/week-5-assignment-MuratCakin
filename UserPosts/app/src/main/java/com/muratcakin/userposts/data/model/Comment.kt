package com.muratcakin.userposts.data.model


import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("body")
    val body: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("postId")
    val postId: Int?
)

data class CommentDTO(
    val body: String?,
    val email: String?,
    val id: Int?,
    val name: String?,
    val postId: Int?,
    var isFavorite: Boolean = false
)