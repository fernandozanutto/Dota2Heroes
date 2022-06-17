package com.fzanutto.dota2heroes.network

import com.fzanutto.dota2heroes.model.Hero

class MockApi : ApiConnection {

    private val heroList = listOf<Hero>()

    override suspend fun getHeroesList(): List<Hero> {
        return heroList
    }
}
