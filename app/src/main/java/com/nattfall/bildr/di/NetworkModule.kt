package com.nattfall.bildr.di

import android.content.Context
import com.nattfall.bildr.R
import com.nattfall.bildr.networking.buildOkHttpClient
import com.nattfall.bildr.networking.buildRetrofitClient
import com.nattfall.bildr.networking.flickr.FlickrRequests
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitConfig(client: OkHttpClient): Retrofit = buildRetrofitClient(client)

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient = buildOkHttpClient(
        apiKey = context.getString(R.string.flickr_api_key)
    )

    @Provides
    fun provideFlickrRequests(retrofit: Retrofit): FlickrRequests =
        retrofit.create(FlickrRequests::class.java)
}
