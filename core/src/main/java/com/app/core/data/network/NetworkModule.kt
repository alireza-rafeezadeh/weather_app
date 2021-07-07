package com.app.core.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val BASE_URL = "http://api.weatherapi.com/v1/"

    @Provides
    @Singleton
    fun provideRetrofit(mosh: Moshi): Retrofit {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(mosh))
            .baseUrl(BASE_URL)
            .build()
        return retrofit
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

}