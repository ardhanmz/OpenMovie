package com.ardhanmz.openmovie.di


import com.ardhanmz.openmovie.data.services.DetailService
import com.ardhanmz.openmovie.data.services.DiscoverService
import com.ardhanmz.openmovie.data.services.GenreServices
import com.ardhanmz.openmovie.data.services.ReviewService
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidApplication
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 15L
private const val WRITE_TIMEOUT = 15L
private const val READ_TIMEOUT = 15L
private const val ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjY2UyMjg4NWE0YWEwYzI0M2JkN2M3YzczMGU1Y2QzZSIsInN1YiI6IjYwYWI5ZWUyOGM0NGI5MDA3OTJhZjg2NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.lVCm_MYQTYdgRuwmkrfs3oQdfpDdSmFBNg4NfkooQDY"
private const val BASE_URL = "https://api.themoviedb.org/3/"
val ApiModule = module {
    single(createdAtStart = false) {
        get<Retrofit>().create(GenreServices::class.java)
    }
    single(createdAtStart = false) {
        get<Retrofit>().create(DiscoverService::class.java)
    }
    single(createdAtStart = false) {
        get<Retrofit>().create(DetailService::class.java)
    }
    single(createdAtStart = false) {
        get<Retrofit>().create(ReviewService::class.java)
    }
    single { Cache(androidApplication().cacheDir, 10L * 1024 * 1024) }
    single { GsonBuilder().create() }
    single { retrofitHttpClient() }
    single { retrofitBuilder() }
    single {
        Interceptor { chain ->
            chain.proceed(chain.request().newBuilder().apply {
                header("Accept", "application/json")
                header("Authorization", "Bearer $ACCESS_TOKEN")
            }.build())
        }
    }

}
private fun Scope.retrofitBuilder(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(get()))
        //.addCallAdapterFactory(RxJava2CallAdapterFactory.create()) krn sudah pakai --> Coroutines
        .client(get())
        .build()
}
private fun Scope.retrofitHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        cache(get())
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        retryOnConnectionFailure(true)
        addInterceptor(get())
        addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.HEADERS
            }
            else {
                HttpLoggingInterceptor.Level.NONE
            }
        })
    }.build()
}