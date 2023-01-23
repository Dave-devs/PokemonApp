package com.example.pokemonapplication.feature_pokemon.domain

import com.example.pokemonapplication.feature_pokemon.data.remote.responses.Pokemon
import com.example.pokemonapplication.feature_pokemon.data.remote.responses.PokemonList
import com.example.pokemonapplication.feature_pokemon.domain.util.Resource

interface PokemonRepository {

    suspend fun getPokemonLists(limit: Int, offset: Int): Resource<PokemonList>
    suspend fun getPokemonDetails(pokemonName: String): Resource<Pokemon>
}