package com.example.comics.application.domain.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class UserRepository @Inject constructor() {
    private val api = Retrofit.Builder()
        .baseUrl("https://gateway.marvel.com/v1/public/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getApi(): Retrofit? {
        return api
    }
}