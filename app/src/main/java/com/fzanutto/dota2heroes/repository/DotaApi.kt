package com.fzanutto.dota2heroes.repository

import com.fzanutto.dota2heroes.model.Hero
import retrofit2.http.GET

interface DotaApi: ApiConnection {

    val baseUrl = "https://api.opendota.com"

    @GET("api/heroStats")
    override suspend fun getHeroesList(): List<Hero>
}