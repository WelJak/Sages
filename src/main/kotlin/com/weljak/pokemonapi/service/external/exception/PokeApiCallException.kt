package com.weljak.pokemonapi.service.external.exception

import java.lang.RuntimeException

class PokeApiCallException(message: String): RuntimeException(message)