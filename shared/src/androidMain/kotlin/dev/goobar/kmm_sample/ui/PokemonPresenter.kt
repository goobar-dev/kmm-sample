package dev.goobar.kmm_sample.ui

import dev.goobar.kmm_sample.PokemonNetworkClient
import dev.goobar.kmm_sample.data.Pokemon
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

actual class PokemonPresenter {
  private val scope = MainScope()
  private val networkClient = PokemonNetworkClient()

  val _state: MutableStateFlow<List<Pokemon>> = MutableStateFlow(listOf())
  val state = _state.asStateFlow()

  init {
    scope.launch {
      _state.emit(networkClient.fetchPokemon(9))
    }
  }
}