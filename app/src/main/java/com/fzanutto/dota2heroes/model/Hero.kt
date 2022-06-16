package com.fzanutto.dota2heroes.model

data class Hero(
    val id: Int,
    val name: String,
    val primaryAttribute: HeroAttribute,
    val roles: List<String>,
    val legs: Int,
    val img: String? = null,
    val icon: String? = null,
    val moveSpeed: Int,
    val baseStr: Int,
    val baseAgi: Int,
    val baseInt: Int,
    val strGain: Double,
    val agiGain: Double,
    val intGain: Double,
    val attackType: AttackType,
    val attackRange: Int,
    val baseAttackMin: Int,
    val baseAttackMax: Int
)
