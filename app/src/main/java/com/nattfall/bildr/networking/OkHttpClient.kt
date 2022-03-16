package com.nattfall.bildr.networking

import okhttp3.OkHttpClient

fun buildOkHttpClient(apiKey: String) = OkHttpClient
    .Builder()
    .addInterceptor { chain ->
        var request = chain.request()
        val modifiedApiKeyUrl = request
            .url()
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
