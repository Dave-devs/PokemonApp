package com.example.pokemonapplication.feature_pokemon.domain

import com.example.pokemonapplication.feature_pokemon.data.remote.pokemons.Pokemon
import com.example.pokemonapplication.feature_pokemon.data.remote.pokemons.PokemonList
import com.example.pokemonapplication.feature_pokemon.domain.util.Resource

interface PokemonRepository {

    suspend fun getPokemonLists(limit: Int, offset: Int): Resource<PokemonList>
    suspend fun getPokemonDetails(pokemonName: String): Resource<Pokemon>
}