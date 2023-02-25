package com.github.quanticheart.intellijplugincleantree.wizard.others.retrofit2

fun retrofitTemplate(
    applicationPackage: String?,
    featurePackage: String,
    featureName: String
) = """
package $featurePackage

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import ${applicationPackage}.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val CACHE_CONTROL_HEADER = "Cache-Control"
const val CACHE_TIME = 5

object HttpConstants {
    const val baseUrl: String = ""
    const val authKey: String = ""
    const val cacheSize = 5 * 1024 * 1024L // 5 MB de cache
}

internal class ${featureName}(private val application: Context) {

    private val gson: Gson by lazy { GsonBuilder().create() }

    private val okHttp: OkHttpClient by lazy {
        val client = OkHttpClient.Builder()
            .cache(cacheSize())
            .addNetworkInterceptor(CacheInterceptor)
            .addInterceptor(AuthInterceptor())

        val log = HttpLoggingInterceptor()
        when (BuildConfig.DEBUG) {
            true ->
                log.level = HttpLoggingInterceptor.Level.BODY
            false -> log.level = HttpLoggingInterceptor.Level.NONE
        }
        client.addInterceptor(log)

        client.build()
    }

    fun newInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(HttpConstants.baseUrl)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun cacheSize(): Cache {
        return Cache(application.cacheDir, HttpConstants.cacheSize)
    }
}

internal class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("api-token", HttpConstants.authKey)
        val request = requestBuilder.build()
        val response = chain.proceed(request)
        if (response.code == 401) {
            Log.e("Auth", "Error API KEY")
        }
        return response
    }
}

internal object CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val originalResponse = chain.proceed(request)

        val cacheControl = CacheControl.Builder()
            .maxAge(CACHE_TIME, TimeUnit.MINUTES)
            .build()

        return originalResponse.newBuilder()
            .header(CACHE_CONTROL_HEADER, cacheControl.toString())
            .build()
    }
}

class HttpClient(private val retrofit: Retrofit) {
    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}
"""
