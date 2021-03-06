package com.fzanutto.dota2heroes.model

import android.content.Context
import com.fzanutto.dota2heroes.R

enum class HeroAttribute(val value: String) {
    Agi("agi"),
    Str("str"),
    Int("int"),
    Unknown("");

    companion object {
        fun fromString(attrName: String): HeroAttribute {
            return values().find { it.value == attrName } ?: Unknown
        }
    }

    fun toString(context: Context): String {
        return when (this) {
            Agi -> context.getString(R.string.agility_attribute)
            Str -> context.getString(R.string.strength_attribute)
            Int -> context.getString(R.string.intelligence_attribute)
            Unknown -> "-"
        }
    }

    fun getIcon(): kotlin.Int {
        return when (this) {
            Agi -> R.drawable.hero_agility
            Str -> R.drawable.hero_strength
            Int -> R.drawable.hero_intelligence
            Unknown -> 0
        }
    }
}
