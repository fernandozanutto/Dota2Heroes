package com.fzanutto.dota2heroes.repository

import com.fzanutto.dota2heroes.model.Hero

class MockApi: ApiConnection {

    val heroList = listOf(
        Hero(1, "Anti-Mage", "Agility", listOf("Carry"), 2),
        Hero(1, "Broodmother", "Agility", listOf("Carry, Pusher"), 8, "https://api.opendota.com/apps/dota2/images/dota_react/heroes/broodmother.png?"),
        Hero(1, "Luna", "Agility", listOf("Carry"), 2, "https://api.opendota.com/apps/dota2/images/dota_react/heroes/luna.png?"),
    )
    override suspend fun getHeroesList(): List<Hero> {
        return heroList
    }
}