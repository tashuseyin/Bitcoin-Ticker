package com.example.bitcoin_ticker.di

import com.example.bitcoin_ticker.core.validation.EmailValidation
import com.example.bitcoin_ticker.core.validation.PasswordValidation
import com.example.bitcoin_ticker.data.auth.FirebaseAuth
import com.example.bitcoin_ticker.data.local.database.CoinDao
import com.example.bitcoin_ticker.data.local.datasource.LocalDataSourceImpl
import com.example.bitcoin_ticker.data.remote.CoinApiService
import com.example.bitcoin_ticker.data.remote.datasource.RemoteDataSourceImpl
import com.example.bitcoin_ticker.data.repository.CoinRepositoryImpl
import com.example.bitcoin_ticker.data.repository.FirebaseRepositoryImpl
import com.example.bitcoin_ticker.domain.auth.AuthenticationProxy
import com.example.bitcoin_ticker.domain.datasource.local.LocalDataSource
import com.example.bitcoin_ticker.domain.datasource.remote.RemoteDataSource
import com.example.bitcoin_ticker.domain.repository.CoinRepository
import com.example.bitcoin_ticker.domain.repository.FirebaseRepository
import com.google.firebase.firestore.FirebaseFirestore
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
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideAuthenticationProxy(): AuthenticationProxy = FirebaseAuth()

    @Provides
    @Singleton
    fun provideFirebaseRepository(
        authenticationProxy: AuthenticationProxy,
        firebaseFireStore: FirebaseFirestore
    ): FirebaseRepository = FirebaseRepositoryImpl(authenticationProxy, firebaseFireStore)

    @Provides
    @Singleton
    fun provideEmailValidation(): EmailValidation = EmailValidation()

    @Provides
    @Singleton
    fun providePasswordValidation(): PasswordValidation = PasswordValidation()

    @Provides
    @Singleton
    fun provideRemoteDataSource(coinApiService: CoinApiService): RemoteDataSource =
        RemoteDataSourceImpl(coinApiService)

    @Provides
    @Singleton
    fun provideLocalDataSource(coinDao: CoinDao): LocalDataSource = LocalDataSourceImpl(coinDao)

    @Provides
    @Singleton
    fun provideCoinRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): CoinRepository = CoinRepositoryImpl(remoteDataSource, localDataSource)
}