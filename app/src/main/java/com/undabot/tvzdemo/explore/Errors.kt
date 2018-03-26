package com.undabot.tvzdemo.explore

import java.io.IOException

sealed class Error(val message: String)

class NetworkError : Error("Oh no internet down")
class RequestError : Error("Oh no request failed")


fun errorFrom(throwable: Throwable) :Error{
    return when(throwable){
        is IOException -> NetworkError()
        else  -> RequestError()
    }
}


