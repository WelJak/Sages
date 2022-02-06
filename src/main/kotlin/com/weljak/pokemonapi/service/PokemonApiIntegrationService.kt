package com.weljak.pokemonapi.service

import com.weljak.pokemonapi.domain.model.DamageType
import com.weljak.pokemonapi.api.model.GetDamageRelationsResponse

interface PokemonApiIntegrationService {
    fun getDamageRelations(attackType: DamageType, pokemonTypes: Set<DamageType>): GetDamageRelationsResponse
}