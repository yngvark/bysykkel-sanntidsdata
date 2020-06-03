package com.yngvark.sanntid.gather_data

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.routing

@KtorExperimentalLocationsAPI
fun Application.rootRouting(): Routing = routing {
    get("/") {
        call.respondText("OK!", contentType = ContentType.Text.Plain)
    }
}
