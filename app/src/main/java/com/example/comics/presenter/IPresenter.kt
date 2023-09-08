package com.example.comics.presenter

import com.example.comics.application.domain.model.ItemModel

interface IPresenter {

    fun setupList(list: ItemModel)

    fun error()
}