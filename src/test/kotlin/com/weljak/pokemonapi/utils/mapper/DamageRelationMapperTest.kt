package com.weljak.pokemonapi.utils.mapper

import com.weljak.pokemonapi.api.model.GetDamageRelationsResponse
import com.weljak.pokemonapi.domain.model.DamageType
import com.weljak.pokemonapi.domain.model.DamageTypeDetails
import com.weljak.pokemonapi.domain.model.PokeApiResponse
import org.junit.jupiter.api.Test

class DamageRelationMapperTest {
    @Test
    fun shouldProperlyMapResponse() {
        //given
        val givenResponse = PokeApiResponse(mapOf(
            "double_damage_to" to listOf(DamageTypeDetails("dark", "url"), DamageTypeDetails("fairy", "url")),
            "half_damage_to" to listOf(DamageTypeDetails("dark", "url"), DamageTypeDetails("fairy", "url")),
            "no_damage_to" to listOf(DamageTypeDetails("dark", "url"), DamageTypeDetails("fairy", "url"))
        ))
        val givenAttackType = DamageType.Dragon
        val givenPokemonTypes = setOf(DamageType.Dark, DamageType.Fairy, DamageType.Fighting)
        val givenRemainingDamageTypes = DamageType.values().toHashSet()
        givenRemainingDamageTypes.remove(DamageType.Dark)
        givenRemainingDamageTypes.remove(DamageType.Fairy)

        val expectedResult = GetDamageRelationsResponse(
            DamageType.Dragon,
            mapOf(
                "doubleDamageTo" to setOf(DamageType.Dark, DamageType.Fairy),
                "halfDamageTo" to setOf(DamageType.Dark, DamageType.Fairy),
                "noDamageTo" to setOf(DamageType.Dark, DamageType.Fairy),
                "normalDamageTo" to givenRemainingDamageTypes
            )
        )

        // when
        val result = DamageRelationMapper.toDamageRelationsResponse(givenResponse, givenAttackType, givenPokemonTypes)

        //then
        assert(expectedResult == result)
    }
}