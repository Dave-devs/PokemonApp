package com.example.pokemonapplication.feature_pokemon.data.remote.responses

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)