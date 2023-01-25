package com.example.pokemonapplication.feature_pokemon.presentation.pokemon_detail_screen.components

import androidx.compose.foundation.layout.offset
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pokemonapplication.feature_pokemon.data.remote.responses.Pokemon
import com.example.pokemonapplication.feature_pokemon.domain.util.Resource

@Composable
fun PokemonStateWrapper(
    pokemonDetail: Resource<Pokemon>,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier
) {
    when(pokemonDetail) {
        is Resource.Success -> {
            PokemonDetailSection(
                pokemonDetail = pokemonDetail.data!!,
                modifier = Modifier
                    .offset(y = (-20).dp)
            )
        }
        is Resource.Error -> {
            Text(
                text = pokemonDetail.message!!,
                color = Color.Red,
                modifier = modifier
            )
        }
        is Resource.Loading -> {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = loadingModifier
            )
        }
    }
}