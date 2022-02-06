package com.weljak.pokemonapi.domain.model

import com.google.gson.annotations.SerializedName

data class PokeApiResponse(
    @SerializedName("damage_relations")
    val damageRelations: Map<String, List<DamageTypeDetails>>
)