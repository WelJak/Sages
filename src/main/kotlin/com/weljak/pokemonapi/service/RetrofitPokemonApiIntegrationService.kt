package com.weljak.pokemonapi.service

import com.weljak.pokemonapi.api.model.GetDamageRelationsResponse
import com.weljak.pokemonapi.domain.model.DamageType
import com.weljak.pokemonapi.domain.model.PokeApiResponse
import com.weljak.pokemonapi.service.external.PokeApiService
import com.weljak.pokemonapi.service.external.exception.PokeApiCallException
import com.weljak.pokemonapi.utils.mapper.DamageRelationMapper
import mu.KotlinLogging
import org.springframework.stereotype.Service
import retrofit2.Retrofit

@Service
class RetrofitPokemonApiIntegrationService(retrofit: Retrofit): PokemonApiIntegrationService {
    private val pokeApiService = retrofit.create(PokeApiService::class.java)
    private val log = KotlinLogging.logger{}

    override fun getDamageRelations(attackType: DamageType, pokemonTypes: Set<DamageType>): GetDamageRelationsResponse {
        log.info("Calling poke api to get $attackType details")
        val call = pokeApiService.getDamageRelations(attackType.type)
        val response = call.execute()
        if (response.isSuccessful) {
            val body = response.body()!!
            return DamageRelationMapper.toDamageRelationsResponse(body, attackType, pokemonTypes)
        }
        throw PokeApiCallException("Error occurred during invoking PokeApiService: ${response.errorBody()}. Code: ${response.code()}")
    }

}