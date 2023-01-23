package com.example.pokemonapplication.feature_pokemon.di

import com.example.pokemonapplication.core.Constants.BASE_URL
import com.example.pokemonapplication.feature_pokemon.data.PokemonRepositoryImpl
import com.example.pokemonapplication.feature_pokemon.data.remote.api.PokemonApi
import com.example.pokemonapplication.feature_pokemon.domain.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): PokemonApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonRepository(api: PokemonApi): PokemonRepository {
        return PokemonRepositoryImpl(api)
    }
}