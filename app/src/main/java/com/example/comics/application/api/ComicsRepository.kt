package com.example.comics.application.api

import com.example.comics.application.config.Config
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