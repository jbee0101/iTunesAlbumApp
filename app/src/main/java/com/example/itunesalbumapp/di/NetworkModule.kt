package com.example.itunesalbumapp.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.itunesalbumapp.data.remote.AlbumApiService
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Module that provides dependencies for the network.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides the base URL for the API.
     *
     * @return The base URL as a string.
     */
    @Provides
    fun provideBaseUrl(): String = "https://itunes.apple.com/"

    /**
     * Provides an instance of [OkHttpClient].
     *
     * @return The [OkHttpClient] instance.
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .build()

    /**
     * Provides an instance of [Moshi].
     */
    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    /**
     * Provides an instance of [Retrofit].
     *
     * @param client The [OkHttpClient] instance.
     * @param baseUrl The base URL for the API.
     * @param moshi The [Moshi] instance.
     * @return The [Retrofit] instance.
     */
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, baseUrl: String, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    /**
     * Provides an instance of [AlbumApiService].
     *
     * @param retrofit The [Retrofit] instance.
     * @return The [AlbumApiService] instance.
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): AlbumApiService =
        retrofit.create(AlbumApiService::class.java)

    /**
     * Provides an instance of [ConnectivityManager].
     *
     * @param context The application context.
     * @return The [ConnectivityManager] instance.
     */
    @Singleton
    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}
