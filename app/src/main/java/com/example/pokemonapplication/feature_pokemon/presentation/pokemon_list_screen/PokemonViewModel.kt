package com.example.pokemonapplication.feature_pokemon.presentation.pokemon_list_screen

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.example.pokemonapplication.core.Constants.PAGE_SIZE
import com.example.pokemonapplication.feature_pokemon.domain.PokemonRepository
import com.example.pokemonapplication.feature_pokemon.domain.model.PokemonListEntry
import com.example.pokemonapplication.feature_pokemon.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokeRepository: PokemonRepository
): ViewModel() {

    //Pagination function
    private var currentPage = 0

    var pokemonList = mutableStateOf<List<PokemonListEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    private var cachedPokemonList = listOf<PokemonListEntry>()
    private var isSearchStarted = true
    var isSearching = mutableStateOf(false)

    init {
        loadPokemonPagination()
    }

    fun searchPokemonList(query: String) {
        val listToSearch = if(isSearchStarted) {
            pokemonList.value
        } else {
            cachedPokemonList
        }

        viewModelScope.launch(Dispatchers.Default){
            if (query.isEmpty()) {
                pokemonList.value = cachedPokemonList
                isSearching.value = false
                isSearchStarted = true
                return@launch
            }
            val results = listToSearch.filter {
                it.pokemonName.contains(query.trim(), ignoreCase = true) ||
                        it.number.toString() == query.trim()
            }
            if(isSearchStarted) {
                cachedPokemonList = pokemonList.value
                isSearchStarted = false
            }
            pokemonList.value = results
            isSearching.value = true
        }
    }

    fun loadPokemonPagination() {
        viewModelScope.launch {
            isLoading.value = true
            when(val result = pokeRepository.getPokemonLists(PAGE_SIZE, currentPage  * PAGE_SIZE)) {
                is Resource.Success -> {
                    endReached.value = currentPage * PAGE_SIZE >= result.data!!.count
                    val pokemonEntries = result.data.results.mapIndexed { index, entry ->
                        val number = if(entry.url.endsWith("/")) {
                            entry.url.dropLast(1).takeLastWhile {it.isDigit()}
                        } else {
                            entry.url.takeLastWhile {it.isDigit()}
                        }
                        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                        PokemonListEntry(entry.name.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        }, url, number.toInt())
                    }
                    currentPage++

                    loadError.value = ""
                    isLoading.value = false
                    pokemonList.value += pokemonEntries
                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
                else -> {}
            }
        }
    }

    //Function to determine the dominant color with Palette Library
    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888,true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let{ colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}