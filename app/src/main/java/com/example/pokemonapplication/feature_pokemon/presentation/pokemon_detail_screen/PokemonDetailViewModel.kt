package com.example.pokemonapplication.feature_pokemon.presentation.pokemon_detail_screen

import androidx.lifecycle.ViewModel
import com.example.pokemonapplication.feature_pokemon.data.remote.responses.Pokemon
import com.example.pokemonapplication.feature_pokemon.domain.PokemonRepository
import com.example.pokemonapplication.feature_pokemon.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel()
class PokemonDetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel() {

    suspend fun getPokemonDetails(pokemonName: String): Resource<Pokemon> {
        return pokemonRepository.getPokemonDetails(pokemonName)
    }
}