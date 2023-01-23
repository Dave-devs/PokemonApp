package com.example.pokemonapplication.feature_pokemon.data.remote.responses

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)