package com.fzanutto.dota2heroes.model

import android.content.Context

enum class PrimaryAttribute(val value: String) {
    Agi("agi"),
    Str("str"),
    Int("int"),
    Unknown("");

    companion object {
        fun fromString(attrName: String): PrimaryAttribute {
            return values().find { it.value == attrName } ?: Unknown
        }
    }

    fun toString(context: Context): String {
        return when (this) {
            Agi -> "Agility"
            Str -> "Strength"
            Int -> "Intelligence"
            Unknown -> "-"
        }
    }
}