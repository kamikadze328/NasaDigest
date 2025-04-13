package com.kamikadze328.nasadigest.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json

val client = HttpClient {
    install(ContentNegotiation) {
        json(
            json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
                explicitNulls = false
            }
        )
    }
}

suspend inline fun <reified T> HttpClient.get(url: String): Result<T> {
    val response = get(url)
    return if (response.status.isSuccess()) {
        Result.Success(response.body())
    } else {
        Result.Error(Exception("Error: ${response.body<String>()}"))
    }
}