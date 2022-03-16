package com.nattfall.bildr.networking

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

fun buildOkHttpClient(apiKey: String) = OkHttpClient
    .Builder()
    .addInterceptor(HttpLoggingInterceptor { message -> Log.d("Bildr-network", message) })
    .addInterceptor { chain ->
        val request = chain.request()
        val modifiedApiKeyUrl = request
            .url
            .newBuilder()
            .addQueryParameter("apiKey", apiKey)
            .addQueryParameter("format", "json")
            .build()

        val updatedRequest = request
            .newBuilder()
            .url(modifiedApiKeyUrl)
            .build()

        chain.proceed(updatedRequest)
    }
    .build()
