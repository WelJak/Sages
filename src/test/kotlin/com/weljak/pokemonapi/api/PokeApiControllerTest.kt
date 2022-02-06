package com.weljak.pokemonapi.api

import com.weljak.pokemonapi.utils.response.PokemonApiIntegrationResponse
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import java.net.URI

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PokeApiControllerTest {
    @LocalServerPort
    private var port: Int = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun shouldReturnStatusOkWhenRequestIsValid() {
        //given
        val givenUrl = "http://localhost:$port/damage/relations?attackType=Dark&&pokemonTypes=Dark"

        //when
        val response = restTemplate.getForEntity(givenUrl, PokemonApiIntegrationResponse::class.java)

        //then
        assert(response.statusCode == HttpStatus.OK)
    }

    @Test
    fun shouldReturnStatusBadRequestWhenAttackTypeIsInvalid() {
        //given
        val givenUrl = "http://localhost:$port/damage/relations?attackType=AnotherValue&&pokemonTypes=Dark"

        //when
        val response = restTemplate.getForEntity(givenUrl, PokemonApiIntegrationResponse::class.java)

        //then
        assert(response.statusCode == HttpStatus.BAD_REQUEST)
    }

    @Test
    fun shouldReturnStatusBadRequestWhenPokemonTypesAreInvalid() {
        //given
        val givenUrl = "http://localhost:$port/damage/relations?attackType=AnotherValue&&pokemonTypes=InvalidValues"

        //when
        val response = restTemplate.getForEntity(givenUrl, PokemonApiIntegrationResponse::class.java)

        //then
        assert(response.statusCode == HttpStatus.BAD_REQUEST)
    }

    @Test
    fun shouldReturnStatusBadRequestWhenAttackTypeIsNull() {
        //given
        val givenUrl = "http://localhost:$port/damage/relations?pokemonTypes=Dark"

        //when
        val response = restTemplate.getForEntity(givenUrl, PokemonApiIntegrationResponse::class.java)

        //then
        assert(response.statusCode == HttpStatus.BAD_REQUEST)
    }

    @Test
    fun shouldReturnStatusBadRequestWhenPokemonTypesAreNull() {
        //given
        val givenUrl = "http://localhost:$port/damage/relations?attackType=AnotherValue"

        //when
        val response = restTemplate.getForEntity(givenUrl, PokemonApiIntegrationResponse::class.java)

        //then
        assert(response.statusCode == HttpStatus.BAD_REQUEST)
    }

}