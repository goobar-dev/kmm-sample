package dev.goobar.kmm_sample.data

import kotlinx.serialization.Serializable

@Serializable
internal data class PokemonRequestResponse(
  val count: Int,
  val results: List<Pokemon>
)
