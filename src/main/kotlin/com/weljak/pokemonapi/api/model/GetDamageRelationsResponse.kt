package com.weljak.pokemonapi.api.model

import com.weljak.pokemonapi.domain.model.DamageType

data class GetDamageRelationsResponse(
    val attackType: DamageType,
    val damageRelations: Map<String, Set<DamageType>>
)
