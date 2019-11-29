package ru.netology.crud.dto

class PostRequestDto(
    val id: Long,
    val author: String,
    val content: String? = null
)