package com.example.pokemonapplication.feature_pokemon.data

import com.example.pokemonapplication.feature_pokemon.data.remote.api.PokemonApi
import com.example.pokemonapplication.feature_pokemon.data.remote.pokemons.Pokemon
import com.example.pokemonapplication.feature_pokemon.data.remote.pokemons.PokemonList
import com.example.pokemonapplication.feature_pokemon.domain.PokemonRepository
import com.example.pokemonapplication.feature_pokemon.domain.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.HttpException
import java.io.IOException

@ActivityScoped
class PokemonRepositoryImpl(
    private val api: PokemonApi
): PokemonRepository {

    override suspend fun getPokemonLists(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonLists(limit,offset)
        } catch (_: HttpException) {
            return Resource.Error("An unknown error occurred")
        } catch (_: IOException) {
            return Resource.Error("Could;t reach the server, poor internet connection!")
        }
        return Resource.Success(response)
    }

    override suspend fun getPokemonDetails(pokemonName: String): Resource<Pokemon> {
        val response = try {
            api.getPokemonDetails(pokemonName)
        } catch (_: Exception) {
            return Resource.Error("An unknown error occurred")
        } catch (_: IOException) {
            return Resource.Error("Could;t reach the server, poor internet connection!")
        }
        return Resource.Success(response)
    }
}