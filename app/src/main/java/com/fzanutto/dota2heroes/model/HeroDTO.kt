package com.fzanutto.dota2heroes.model

data class HeroDTO(
    val `1_pick`: Int,
    val `1_win`: Int,
    val `2_pick`: Int,
    val `2_win`: Int,
    val `3_pick`: Int,
    val `3_win`: Int,
    val `4_pick`: Int,
    val `4_win`: Int,
    val `5_pick`: Int,
    val `5_win`: Int,
    val `6_pick`: Int,
    val `6_win`: Int,
    val `7_pick`: Int,
    val `7_win`: Int,
    val `8_pick`: Int,
    val `8_win`: Int,
    val agi_gain: Double,
    val attack_range: Int,
    val attack_rate: Double,
    val attack_type: String,
    val base_agi: Int,
    val base_armor: Int,
    val base_attack_max: Int,
    val base_attack_min: Int,
    val base_health: Int,
    val base_health_regen: Double,
    val base_int: Int,
    val base_mana: Int,
    val base_mana_regen: Int,
    val base_mr: Int,
    val base_str: Int,
    val cm_enabled: Boolean,
    val hero_id: Int,
    val icon: String,
    val id: Int,
    val img: String,
    val int_gain: Double,
    val legs: Int,
    val localized_name: String,
    val move_speed: Int,
    val name: String,
    val null_pick: Int,
    val null_win: Int,
    val primary_attr: String,
    val pro_ban: Int,
    val pro_pick: Int,
    val pro_win: Int,
    val projectile_speed: Int,
    val roles: List<String>,
    val str_gain: Double,
    val turbo_picks: Int,
    val turbo_wins: Int,
    val turn_rate: Any
) {

    fun toHero(): Hero {
        return Hero(
            id, name, primary_attr, roles, legs, img, icon
        )
    }
}