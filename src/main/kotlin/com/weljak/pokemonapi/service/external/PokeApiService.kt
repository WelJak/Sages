package com.weljak.pokemonapi.service.external

import com.weljak.pokemonapi.domain.model.DamageType
import com.weljak.pokemonapi.domain.model.PokeApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {
    @GET("type/{damageType}")
    fun getDamageRelations(@Path("damageType") damageType: String): Call<PokeApiResponse>
}