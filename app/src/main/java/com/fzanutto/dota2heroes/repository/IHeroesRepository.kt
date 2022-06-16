package com.fzanutto.dota2heroes.repository

import com.fzanutto.dota2heroes.model.Hero
import com.fzanutto.dota2heroes.network.ApiConnection

interface IHeroesRepository {

    val api: ApiConnection
    suspend fun getHeroList(): List<Hero>
}
