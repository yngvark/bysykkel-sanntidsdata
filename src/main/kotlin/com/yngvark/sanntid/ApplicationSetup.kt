package com.yngvark.sanntid

import com.fasterxml.jackson.databind.SerializationFeature
import com.yngvark.sanntid.expose_joined_data.StationAvailabilityStore
import com.yngvark.sanntid.expose_joined_data.stationAvailabilityRouting
import com.yngvark.sanntid.gather_data.BysykkelApiImpl
import com.yngvark.sanntid.gather_data.HttpUtil
import com.yngvark.sanntid.gather_data.rootRouting
import com.yngvark.sanntid.join_data.BysykkelStationJoiner
import com.yngvark.sanntid.show_joined_data.ConsolePresenter
import com.yngvark.sanntid.show_joined_data.OutputPrinterImpl
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.locations.KtorExperimentalLocationsAPI
import okhttp3.OkHttpClient

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalLocationsAPI
@Suppress("unused")
// Referenced in application.conf
fun Application.module() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    rootRouting()
    val store = StationAvailabilityStore()
    stationAvailabilityRouting(store)

    runApp(store)
}

fun runApp(store:StationAvailabilityStore) {
    val appRunner = ApplicationRunner(
            BysykkelApiImpl(HttpUtil(OkHttpClient())),
            BysykkelStationJoiner(),
            ConsolePresenter(OutputPrinterImpl()),
            store
    )

    appRunner.run()
}
