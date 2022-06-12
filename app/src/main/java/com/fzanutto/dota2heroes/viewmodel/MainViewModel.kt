package com.fzanutto.dota2heroes.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fzanutto.dota2heroes.model.Hero
import com.fzanutto.dota2heroes.repository.ApiConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val api: ApiConnection): ViewModel() {

    class MainViewModelFactory(private val api: ApiConnection): ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(api) as T
        }

    }

    val heroList = mutableStateListOf<Hero>()


    fun loadHeroList() {
        viewModelScope.launch(Dispatchers.IO) {
            heroList.addAll(api.getHeroesList())
        }
    }
}