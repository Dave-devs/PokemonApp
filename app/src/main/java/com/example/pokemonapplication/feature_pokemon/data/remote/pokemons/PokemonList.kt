package com.example.pokemonapplication.feature_pokemon.data.remote.pokemons

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)