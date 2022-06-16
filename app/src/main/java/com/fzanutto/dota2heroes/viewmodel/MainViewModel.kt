package com.fzanutto.dota2heroes.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fzanutto.dota2heroes.model.Hero
import com.fzanutto.dota2heroes.repository.IHeroesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: IHeroesRepository) : ViewModel() {

    class MainViewModelFactory(private val repository: IHeroesRepository) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(IHeroesRepository::class.java).newInstance(repository)
        }
    }

    val heroList = mutableStateListOf<Hero>()

    val sortBy = mutableStateOf("ID")
    val sortOrder = mutableStateOf(true)

    fun loadHeroList() {
        viewModelScope.launch(Dispatchers.IO) {
            heroList.clear()

            val heroes = repository.getHeroList()
            heroList.addAll(heroes)
        }
    }

    fun reverseList() {
        val currentList = heroList.toMutableList()
        currentList.reverse()

        heroList.clear()
        heroList.addAll(currentList)

        sortOrder.value = !sortOrder.value
    }
}
