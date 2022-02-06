package com.weljak.pokemonapi.utils.mapper

import com.weljak.pokemonapi.api.model.GetDamageRelationsResponse
import com.weljak.pokemonapi.domain.model.DamageType
import com.weljak.pokemonapi.domain.model.PokeApiResponse
import java.util.*
import kotlin.collections.HashMap

object DamageRelationMapper {
    private val damageModifiersNamesMapping = mapOf("double_damage_to" to "doubleDamageTo", "half_damage_to" to "halfDamageTo", "no_damage_to" to "noDamageTo")

    fun toDamageRelationsResponse(response: PokeApiResponse, attackType: DamageType, pokemonTypes: Set<DamageType>): GetDamageRelationsResponse {
        val damageRelations = HashMap<String, Set<DamageType>>()
        damageModifiersNamesMapping.forEach { damageModifierName ->
            val relation = response.damageRelations[damageModifierName.key]
            relation?.let {
                it.forEach { modifier ->
                    val damageType = DamageType.valueOf(capitalize(modifier.name))
                    if (pokemonTypes.contains(damageType)) {
                        if (damageRelations.containsKey(damageModifierName.value)) {
                            damageRelations[damageModifierName.value] = damageRelations[damageModifierName.value]!!.plus(damageType)
                        } else {
                            damageRelations[damageModifierName.value] = setOf(damageType)
                        }
                    }
                }
            }
        }
        return GetDamageRelationsResponse(attackType, damageRelations)
    }

    private fun capitalize(input: String): String {
        return input.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
}