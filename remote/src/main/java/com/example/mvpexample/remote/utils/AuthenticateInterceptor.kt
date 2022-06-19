package com.example.mvpexample.remote.utils

import com.example.mvpexample.remote.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

class AuthenticateInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url

        val currentTimeMillis = System.currentTimeMillis().toString()
        val url: HttpUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("apikey", BuildConfig.MarvelPublicKey)
            .addQueryParameter(
                "hash",
                md5(
                    currentTimeMillis + BuildConfig.MarvelPrivateKey + BuildConfig.MarvelPublicKey
                )
            ).addQueryParameter("ts", currentTimeMillis)
            .build()

        // Request customization: add request headers
        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }

    fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}
