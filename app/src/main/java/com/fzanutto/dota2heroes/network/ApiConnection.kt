package com.fzanutto.dota2heroes.network

interface ApiConnection {
    suspend fun getHeroesList(): Any
}
