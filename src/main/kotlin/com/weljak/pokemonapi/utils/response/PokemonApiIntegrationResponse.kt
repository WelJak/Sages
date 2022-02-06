package com.weljak.pokemonapi.utils.response

data class PokemonApiIntegrationResponse(
    val timestamp: String,
    val path: String,
    val statusCode: Int,
    val status: String,
    val success: Boolean,
    val message: String,
    val payload: Any? = null
)
