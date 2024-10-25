package com.example.pruebatecnica.jokes.di

import com.example.pruebatecnica.jokes.data.repository.JokesRepositoryImp
import com.example.pruebatecnica.jokes.domain.repository.JokesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class JokesModule {
    @Binds
    abstract fun bindJokesRepository(jokesRepositoryImp: JokesRepositoryImp): JokesRepository
}