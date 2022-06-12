package com.fzanutto.dota2heroes.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fzanutto.dota2heroes.model.Hero
import com.fzanutto.dota2heroes.repository.IHeroesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: IHeroesRepository): ViewModel() {

    class MainViewModelFactory(private val repository: IHeroesRepository): ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }

    }

    val heroList = mutableStateListOf<Hero>()

    fun loadHeroList() {
        viewModelScope.launch(Dispatchers.IO) {
            heroList.clear()

            val heroes = repository.getHeroList()
            Log.d("VIEWODEL", heroes.size.toString())
            heroList.addAll(heroes)
        }
    }
}