package com.example.pokemonapplication.feature_pokemon.presentation.pokemon_list_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokemonapplication.R
import com.example.pokemonapplication.feature_pokemon.domain.model.PokemonListEntry
import com.example.pokemonapplication.feature_pokemon.presentation.pokemon_list_screen.component.PokemonList
import com.example.pokemonapplication.feature_pokemon.presentation.pokemon_list_screen.component.SearchBar

@Composable
fun PokemonListScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = modifier.fillMaxSize()
    ) {
        Column{
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.pokemon_logo)
                ,contentDescription = "pokemon_logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            )
            SearchBar(
                hint = "Search...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

            }
            Spacer(modifier = Modifier.height(16.dp))
            PokemonList( navController = navController)
        }
    }
}