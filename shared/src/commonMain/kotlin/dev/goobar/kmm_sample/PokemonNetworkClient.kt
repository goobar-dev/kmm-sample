package dev.goobar.kmm_sample

import dev.goobar.kmm_sample.data.Pokemon
import dev.goobar.kmm_sample.data.PokemonRequestResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.core.use

class PokemonNetworkClient {

  private val client = HttpClient() {
    install(JsonFeature) {
      serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
        this.ignoreUnknownKeys = true
      })
    }
  }

  suspend fun fetchPokemon(count: Int): List<Pokemon> {
    val response:PokemonRequestResponse = client.get("https://pokeapi.co/api/v2/pokemon/?limit=$count")
    return response.results.also { client.close() }
  }
}