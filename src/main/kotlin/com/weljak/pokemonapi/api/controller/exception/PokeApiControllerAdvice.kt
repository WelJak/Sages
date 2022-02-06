package com.weljak.pokemonapi.api.controller.exception

import com.weljak.pokemonapi.utils.response.PokemonApiIntegrationResponse
import com.weljak.pokemonapi.utils.response.PokemonApiIntegrationResponseUtils
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.lang.Exception

@RestControllerAdvice
class PokeApiControllerAdvice {
    private val log = KotlinLogging.logger {  }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleInvalidRequestArgument(exception: Exception, webRequest:ServletWebRequest): ResponseEntity<PokemonApiIntegrationResponse> {
        log.error("Invalid argument for request ${webRequest.request.requestURI}")
        return PokemonApiIntegrationResponseUtils.error(webRequest, null, "Invalid argument for request ${webRequest.request.requestURI}" , HttpStatus.BAD_REQUEST)
    }
}