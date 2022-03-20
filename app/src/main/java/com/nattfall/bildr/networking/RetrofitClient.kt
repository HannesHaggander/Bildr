package com.nattfall.bildr.networking

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val FLICKR_BASE_URL = "https://www.flickr.com/services/rest/"
private val moshiJsonConverter = MoshiConverterFactory.create(
    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
)

fun buildRetrofitClient(domainClient: OkHttpClient) = Retrofit
    .Builder()
    .baseUrl(FLICKR_BASE_URL)
    .addConverterFactory(moshiJsonConverter)
    .client(domainClient)
    .build()
