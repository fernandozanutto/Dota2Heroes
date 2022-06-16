package com.fzanutto.dota2heroes.model

import android.content.Context
import com.fzanutto.dota2heroes.R

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
            Agi -> context.getString(R.string.agility_attribute)
            Str -> context.getString(R.string.strength_attribute)
            Int -> context.getString(R.string.intelligence_attribute)
            Unknown -> "-"
        }
    }

    fun getIcon(): kotlin.Int {
        return when (this) {
            Agi -> R.drawable.dota_agility
            Str -> R.drawable.dota_strength
            Int -> R.drawable.dota_intelligence
            Unknown -> 0
        }
    }
}
