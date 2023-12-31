package com.example.comics.application.domain.model

data class ResultModel(
    val id: Int,
    val title: String,
    val description: String?,
    val image: String?,
    val thumbnail: ThumbnailModel
)