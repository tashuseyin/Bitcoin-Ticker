package com.example.bitcoin_ticker.di

import com.example.bitcoin_ticker.data.auth.FirebaseAuth
import com.example.bitcoin_ticker.data.repository.FirebaseRepositoryImpl
import com.example.bitcoin_ticker.domain.auth.AuthenticationProxy
import com.example.bitcoin_ticker.domain.repository.FirebaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseRepository(): FirebaseRepository = FirebaseRepositoryImpl()

    @Provides
    @Singleton
    fun provideAuthenticationProxy(): AuthenticationProxy  = FirebaseAuth()
}