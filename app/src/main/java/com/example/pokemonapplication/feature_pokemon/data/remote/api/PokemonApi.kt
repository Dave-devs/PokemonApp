package com.example.pokemonapplication.feature_pokemon.data.remote.api

import com.example.pokemonapplication.feature_pokemon.data.remote.responses.Pokemon
import com.example.pokemonapplication.feature_pokemon.data.remote.responses.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonLists(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(
        @Path("name") name: String
    ): Pokemon
}