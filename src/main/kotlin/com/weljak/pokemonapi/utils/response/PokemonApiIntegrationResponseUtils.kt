package com.weljak.pokemonapi.utils.response

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.context.request.ServletWebRequest
import java.time.Clock
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

object PokemonApiIntegrationResponseUtils {
    private val clock: Clock = Clock.systemUTC()
    private val FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ")

    fun success(
        serverHttpRequest: ServletWebRequest,
        payload: Any? = null,
        message: String,
        httpsStatus: HttpStatus
    ): ResponseEntity<PokemonApiIntegrationResponse> {
        val endpoint = serverHttpRequest.request.requestURI
        val response = successPokemonApiResponse(endpoint, payload, message, httpsStatus)
        return ResponseEntity(response, httpsStatus)
    }

    fun error(
        serverHttpRequest: ServletWebRequest,
        payload: Any? = null,
        message: String,
        httpsStatus: HttpStatus
    ): ResponseEntity<PokemonApiIntegrationResponse> {
        val endpoint = serverHttpRequest.request.requestURI
        val response = errorsPokemonApiResponse(endpoint, payload, message, httpsStatus)
        return ResponseEntity(response, httpsStatus)
    }

    private fun successPokemonApiResponse(endpoint: String, payload: Any?, message: String, httpsStatus: HttpStatus): PokemonApiIntegrationResponse {
        return PokemonApiIntegrationResponse(
            getTimestamp(),
            endpoint,
            httpsStatus.value(),
            httpsStatus.name,
            true,
            message,
            payload
        )
    }

    private fun errorsPokemonApiResponse(endpoint: String, payload: Any?, message: String, httpsStatus: HttpStatus): PokemonApiIntegrationResponse {
        return PokemonApiIntegrationResponse(
            getTimestamp(),
            endpoint,
            httpsStatus.value(),
            httpsStatus.name,
            false,
            message,
            payload
        )
    }

    private fun getTimestamp(): String {
        val now = OffsetDateTime.now(clock)
        return FORMATTER.format(now)
    }
}