package com.example.rafaellat.marvelgallery.data.network.provider

import com.example.rafaellat.marvelgallery.BuildConfig
import okhttp3.Interceptor
import java.math.BigInteger
import java.security.MessageDigest

fun makeAddSecurityQueryInterceptor() = Interceptor { chain ->
    val originalRequest = chain.request()
    val timeStamp = System.currentTimeMillis()

    // Url customization: add query parameters
    val url = originalRequest.url().newBuilder()
        .addQueryParameter("apikey", "280c97bd7526e152be5f469c354fd4a5") // 1
        .addQueryParameter("ts", "$timeStamp") //device time in milliseconds. It is used to improve the security of the hash provided in the next query.
            .addQueryParameter("hash", calculatedMd5(timeStamp.toString() + "00e0db131bb51aae7d4177a37dabffb32852a98a"+ "280c97bd7526e152be5f469c354fd4a5")) // 1
        .build()

    // Request customization: set custom url
    val request = originalRequest
        .newBuilder()
        .url(url)
        .build()

    chain.proceed(request)
}

/**
 * Calculate MD5 hash for text
 * @param timeStamp Current timeStamp
 * @return MD5 hash string
 */
fun calculatedMd5(text: String): String { val messageDigest = getMd5Digest(text)
    val md5 = BigInteger(1, messageDigest).toString(16)
    return "0" * (32 - md5.length) + md5 // 1
}

private fun getMd5Digest(str: String): ByteArray = MessageDigest.getInstance("MD5").digest(str.toByteArray())

private operator fun String.times(i: Int) = (1..i).fold("") { acc, _ -> acc + this }