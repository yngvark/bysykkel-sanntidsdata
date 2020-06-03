package com.yngvark.sanntid

import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.server.engine.applicationEngineEnvironment
import io.ktor.server.engine.connector
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.netty.NettyApplicationEngine
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll

@KtorExperimentalLocationsAPI
open class BaseIntTest {
    // Below code fires up our application, so it can be used for integration testing.
    companion object {
        lateinit var server: NettyApplicationEngine

        @BeforeAll
        @JvmStatic
        fun setup() {
            val env = applicationEngineEnvironment {
                module {
                    module()
                }

                connector {
                    host = "0.0.0.0"
                    port = 8081
                }
            }
            server = embeddedServer(Netty, env).start(false)
        }

        @AfterAll
        @JvmStatic
        fun teardown() {
            server.stop(1000, 10000)
        }
    }
}
