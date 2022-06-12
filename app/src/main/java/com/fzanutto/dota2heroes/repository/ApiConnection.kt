package com.fzanutto.dota2heroes.repository

import com.fzanutto.dota2heroes.model.Hero

interface ApiConnection {
    suspend fun getHeroesList(): List<Hero>
}