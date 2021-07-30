package dev.goobar.kmm_sample

import dev.goobar.kmm_sample.data.Pokemon
import dev.goobar.kmm_sample.data.PokemonRequestResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.core.use
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonNetworkClient {

  private val client = HttpClient() {
    install(JsonFeature) {
      serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
        this.ignoreUnknownKeys = true
      })
    }
  }

  suspend fun fetchPokemon(count: Int): List<Pokemon> = withContext(Dispatchers.Default) {
    val response:PokemonRequestResponse = client.get("https://pokeapi.co/api/v2/pokemon/?limit=$count")
    response.results.also { client.close() }
  }
}