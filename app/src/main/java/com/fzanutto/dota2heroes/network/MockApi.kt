package com.fzanutto.dota2heroes.network

import com.fzanutto.dota2heroes.model.Hero
import com.fzanutto.dota2heroes.model.PrimaryAttribute

class MockApi: ApiConnection {

    private val heroList = listOf(
        Hero(1, "Anti-Mage", PrimaryAttribute.Agi, listOf("Carry"), 2),
        Hero(1, "Broodmother", PrimaryAttribute.Agi, listOf("Carry, Pusher"), 8, "https://api.opendota.com/apps/dota2/images/dota_react/heroes/broodmother.png?"),
        Hero(1, "Luna", PrimaryAttribute.Agi, listOf("Carry"), 2, "https://api.opendota.com/apps/dota2/images/dota_react/heroes/luna.png?"),
    )

    override suspend fun getHeroesList(): List<Hero> {
        return heroList
    }
}