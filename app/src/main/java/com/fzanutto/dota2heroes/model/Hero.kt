package com.fzanutto.dota2heroes.model

data class Hero(
    val id: Int,
    val name: String,
    val primaryAttribute: PrimaryAttribute,
    val roles: List<String>,
    val legs: Int,
    val img: String? = null,
    val icon: String? = null
)
