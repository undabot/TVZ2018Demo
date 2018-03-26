package com.undabot.tvzdemo.networking

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("Insert your API KEY here")
        return chain.proceed(chain.request().newBuilder().addHeader("Authorization",
                "Client-ID API_KEY_GOES_HERE")
                .build())
    }
}