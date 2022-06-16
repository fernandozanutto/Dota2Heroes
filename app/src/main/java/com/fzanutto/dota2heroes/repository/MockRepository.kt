package com.fzanutto.dota2heroes.repository

import com.fzanutto.dota2heroes.model.Hero
import com.fzanutto.dota2heroes.network.MockApi

object MockRepository : IHeroesRepository {

    override val api: MockApi by lazy {
        MockApi()
    }

    override suspend fun getHeroList(): List<Hero> {
        return api.getHeroesList()
    }
}
