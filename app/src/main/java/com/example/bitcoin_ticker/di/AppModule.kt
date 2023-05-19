package com.example.bitcoin_ticker.di

import com.example.bitcoin_ticker.core.Constant
import com.example.bitcoin_ticker.core.validation.EmailValidation
import com.example.bitcoin_ticker.core.validation.PasswordValidation
import com.example.bitcoin_ticker.data.auth.FirebaseAuth
import com.example.bitcoin_ticker.data.remote.CoinApiService
import com.example.bitcoin_ticker.data.remote.datasource.RemoteDataSourceImpl
import com.example.bitcoin_ticker.data.repository.CoinRepositoryImpl
import com.example.bitcoin_ticker.data.repository.FirebaseRepositoryImpl
import com.example.bitcoin_ticker.domain.auth.AuthenticationProxy
import com.example.bitcoin_ticker.domain.datasource.RemoteDataSource
import com.example.bitcoin_ticker.domain.repository.CoinRepository
import com.example.bitcoin_ticker.domain.repository.FirebaseRepository
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
    fun provideFirebaseRepository(): FirebaseRepository = FirebaseRepositoryImpl()

    @Provides
    @Singleton
    fun provideAuthenticationProxy(): AuthenticationProxy  = FirebaseAuth()

    @Provides
    @Singleton
    fun provideEmailValidation(): EmailValidation = EmailValidation()

    @Provides
    @Singleton
    fun providePasswordValidation(): PasswordValidation = PasswordValidation()

    @Provides
    @Singleton
    fun provideRetrofit(): CoinApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constant.BASE_URL)
            .build()
            .create(CoinApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(coinApiService: CoinApiService): RemoteDataSource = RemoteDataSourceImpl(coinApiService)

    @Provides
    @Singleton
    fun provideCoinRepository(remoteDataSource: RemoteDataSource): CoinRepository = CoinRepositoryImpl(remoteDataSource)


}