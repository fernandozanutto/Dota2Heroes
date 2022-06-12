package com.fzanutto.dota2heroes.network

import com.fzanutto.dota2heroes.model.HeroDTO
import retrofit2.Response
import retrofit2.http.GET

interface DotaApi: ApiConnection {
    @GET("api/heroStats")
    override suspend fun getHeroesList(): Response<List<HeroDTO>>
}