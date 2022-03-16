package com.nattfall.bildr.networking

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun buildRetrofitClient(domainClient: OkHttpClient) = Retrofit
    .Builder()
    .baseUrl("https://www.flickr.com/services/api/")
    .addConverterFactory(
        MoshiConverterFactory
            .create(
                Moshi
                    .Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            )
    )
    .client(domainClient)
    .build()
