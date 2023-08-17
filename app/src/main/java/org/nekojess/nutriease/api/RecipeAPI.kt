package org.nekojess.nutriease.api

import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun gson(): Gson {
    return GsonBuilder().setLenient().create()
}

fun cache(application: Application): Cache {
    val cacheSize = 10 * 1024 * 1024
    return Cache(application.cacheDir, cacheSize.toLong())
}

private val interceptor = run {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.apply {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }
}

fun okHttpClient(cache: Cache, application: Application): OkHttpClient {
    return OkHttpClient.Builder()
        .addNetworkInterceptor(interceptor)
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .cache(cache)
        .addInterceptor { chain ->
            var request = chain.request()
            request = if (hasNetwork(application)!!)
                request.newBuilder().header("Cache-Control", "public, max-age=5").build()
            else
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()
            chain.proceed(request)
        }
        .build()
}

private fun hasNetwork(context: Context): Boolean? {
    var isConnected: Boolean? = false
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    activeNetwork?.let { isConnected = it.isConnected }
    return isConnected
}

fun retrofit(
    okHttpClient: OkHttpClient,
    url: String = "https://728b-34-91-232-241.ngrok.io"
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson()))
        .build()
}

fun service(retrofit: Retrofit): RecipeService =
    retrofit.create(RecipeService::class.java)