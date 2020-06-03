package com.yngvark.sanntid.gather_data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class HttpUtil(
        private val httpClient:OkHttpClient
) {
    fun get(url: String): String {
        val request = Request.Builder()
            .url(url)
            .build()
        return executeHttpRequest(request)
    }

    inline fun <reified RESULT_TYPE: Any> get(url: String): RESULT_TYPE {
        val json = get(url)
        return Gson().fromJson(json)
    }

    // Thanks to https://stackoverflow.com/a/33381385/915441
    inline fun <reified T> Gson.fromJson(json: String): T = fromJson(json, object: TypeToken<T>() {}.type)

    private fun executeHttpRequest(request: Request): String {
        httpClient.newCall(request).execute().use { response ->
            if (response.isSuccessful) {
                return response.body()?.string() ?: "EMPTY RESPONSE BODY"
            } else {
                throw HttpUtilException(response,
                        "${request.method()} '${request.url()}' failed: ${response.code()}:${response.message()}. "
                        + "Response body was: '${response.body()?.string() ?: "EMPTY"}'"
                )
            }
        }
    }
}

class HttpUtilException(val response: Response, msg: String): RuntimeException(msg)