package com.yngvark.sanntid.expose_joined_data

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.routing

@KtorExperimentalLocationsAPI
fun Application.stationAvailabilityRouting(stationAvailabilityStore: StationAvailabilityStore): Routing = routing {
    get("/station_availability") {
        if (stationAvailabilityStore.stationAvailability == null) {
            call.respond(mapOf("status" to "loading"))
        } else {
            call.respond(stationAvailabilityStore.stationAvailability!!)
        }
    }
}
