package com.fzanutto.dota2heroes.model

enum class AttackType(val value: String) {
    Melee("Melee"),
    Ranged("Ranged"),
    Unknown("-");

    companion object {
        fun fromString(value: String): AttackType {
            return values().find { it.value == value } ?: Unknown
        }
    }
}
