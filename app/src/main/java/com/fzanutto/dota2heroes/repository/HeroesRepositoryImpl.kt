package com.fzanutto.dota2heroes.repository

import android.util.Log
import com.fzanutto.dota2heroes.model.Hero
import com.fzanutto.dota2heroes.network.DotaApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HeroesRepositoryImpl: IHeroesRepository {
    val baseUrl = "https://api.opendota.com/"

    override val api: DotaApi by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DotaApi::class.java)
    }

    override suspend fun getHeroList(): List<Hero> {
        val apiReturn = api.getHeroesList()
        Log.d("VIEWODEL", "${apiReturn.isSuccessful}")
        if (!apiReturn.isSuccessful) return listOf()

        return apiReturn.body()?.map { it.toHero() } ?: listOf()
    }

}