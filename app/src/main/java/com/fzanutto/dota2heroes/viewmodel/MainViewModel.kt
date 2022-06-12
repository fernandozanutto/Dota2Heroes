package com.fzanutto.dota2heroes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fzanutto.dota2heroes.model.Hero
import com.fzanutto.dota2heroes.repository.ApiConnection

class MainViewModel(private val api: ApiConnection): ViewModel() {

    class MainViewModelFactory(private val api: ApiConnection): ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(api) as T
        }

    }

    private val _heroList = MutableLiveData<List<Hero>>()
    val heroList = _heroList

    suspend fun loadHeroList() {
        heroList.postValue(api.getHeroesList())
    }
}