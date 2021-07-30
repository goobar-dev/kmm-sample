package dev.goobar.kmm_sample.ui

import dev.goobar.kmm_sample.PokemonNetworkClient
import dev.goobar.kmm_sample.data.Pokemon
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

actual class PokemonPresenter(callback:(List<Pokemon>) -> Unit) {

  private val scope = MainScope()
  private val networkClient = PokemonNetworkClient()

  init {
    scope.launch {
      val pokemon = networkClient.fetchPokemon(9)
      callback(pokemon)
    }

  }
}