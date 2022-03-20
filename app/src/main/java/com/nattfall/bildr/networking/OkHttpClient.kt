package com.nattfall.bildr.networking

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

private const val PARAM_API_KEY = "api_key"
private const val PARAM_FORMAT = "format"
private const val PARAM_NO_JSON_CALLBACK = "nojsoncallback"
private const val VALUE_JSON = "json"
private const val VALUE_JSON_CALLBACK = "1"

fun buildOkHttpClient(apiKey: String) = OkHttpClient
    .Builder()
    .addInterceptor(bodyLogger)
    .appendApiKeyInterceptor(apiKey = apiKey)
    .appendJsonFormatInterceptor()
    .build()

private fun OkHttpClient.Builder.appendApiKeyInterceptor(apiKey: String): OkHttpClient.Builder {
    return addInterceptor { chain ->
        with(chain.request()) {
            val newUrl = url
                .newBuilder()
                .addQueryParameter(PARAM_API_KEY, apiKey)
                .build()

            val newRequest = newBuilder()
                .url(newUrl)
                .build()

            return@addInterceptor chain.proceed(newRequest)
        }
    }
}

private fun OkHttpClient.Builder.appendJsonFormatInterceptor(): OkHttpClient.Builder {
    return addInterceptor { chain ->
        with(chain.request()) {
            val newUrl = url
                .newBuilder()
                .addQueryParameter(PARAM_FORMAT, VALUE_JSON)
                .addQueryParameter(PARAM_NO_JSON_CALLBACK, VALUE_JSON_CALLBACK)
                .build()

            val newRequest = newBuilder()
                .url(newUrl)
                .build()

            return@addInterceptor chain.proceed(newRequest)
        }
    }
}

private val bodyLogger = HttpLoggingInterceptor { message ->
    Log.d("Bildr-network", message)
}.apply {
    level = HttpLoggingInterceptor.Level.BODY
}