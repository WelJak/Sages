package com.weljak.pokemonapi.api.controller

import com.weljak.pokemonapi.domain.model.DamageType
import com.weljak.pokemonapi.service.PokemonApiIntegrationService
import com.weljak.pokemonapi.utils.response.PokemonApiIntegrationResponse
import com.weljak.pokemonapi.utils.response.PokemonApiIntegrationResponseUtils
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.ServletWebRequest

@RestController
class PokeApiController(private val service: PokemonApiIntegrationService) {
    private val log = KotlinLogging.logger {  }
    @GetMapping("/damage/relations")
    fun getDamageRelations(@RequestParam attackType: DamageType, @RequestParam pokemonTypes: Set<DamageType>, webRequest: ServletWebRequest): ResponseEntity<PokemonApiIntegrationResponse>  {
        log.info("Checking damage relations for attack type $attackType against pokemon types: $pokemonTypes")
        return PokemonApiIntegrationResponseUtils.success(webRequest,
            service.getDamageRelations(attackType, pokemonTypes),
            "Damage relations fetched",
            HttpStatus.OK)
    }
}