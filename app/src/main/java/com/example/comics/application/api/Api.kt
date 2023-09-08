package com.example.comics.application.api

import com.example.comics.application.domain.model.ItemModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("comics")
    suspend fun getComics(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ) : Response<ItemModel>
}