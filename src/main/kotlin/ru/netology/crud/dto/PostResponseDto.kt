package ru.netology.crud.dto

import ru.netology.crud.model.PostModel
import ru.netology.crud.model.PostType

data class PostResponseDto(
    val id: Long,
    val author: String,
    val content: String? = null,
    val likes: Int = 0,
    val postType: PostType = PostType.POST
) {
    companion object {
        fun fromModel(model: PostModel) = PostResponseDto(
            id = model.id,
            author = model.author,
            content = model.content,
            likes = model.likes,
            postType = model.postType
        )
    }
}