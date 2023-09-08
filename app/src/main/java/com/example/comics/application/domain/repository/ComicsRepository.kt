package com.example.comics.application.domain.repository

import com.example.comics.application.api.Api
import com.example.comics.application.api.config.Config
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ComicsRepository @Inject constructor() {
    suspend fun getComics() = Retrofit.Builder()
        .baseUrl(Config.Api.endpoint)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(Api::class.java).getComics(
            apikey = Config.Api.apikey,
            ts = Config.Api.ts,
            hash = Config.Api.hash
        )
}