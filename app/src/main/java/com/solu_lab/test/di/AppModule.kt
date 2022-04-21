package com.solu_lab.test.di

import com.solu_lab.test.data.repo.CoinRepository
import com.solu_lab.test.data.services.CoinService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl("https://demo3231717.mockable.io/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideCoinService(retrofit: Retrofit): CoinService {
        return retrofit.create(CoinService::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(coinService: CoinService): CoinRepository {
        return CoinRepository(coinService)
    }
}