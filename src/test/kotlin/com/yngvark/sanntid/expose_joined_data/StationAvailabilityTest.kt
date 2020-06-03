package com.yngvark.sanntid.expose_joined_data

import com.yngvark.sanntid.BaseIntTest
import com.yngvark.sanntid.gather_data.HttpUtil
import com.yngvark.sanntid.join_data.StationAvailability
import io.ktor.locations.KtorExperimentalLocationsAPI
import okhttp3.OkHttpClient
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

@KtorExperimentalLocationsAPI
class StationAvailabilityTest : BaseIntTest() {
    @Test
    fun `endpoint should return station availability`() {
        // Given
        val httpUtil = HttpUtil(OkHttpClient())

        // When
        val stationAvailabilityList = httpUtil.get<List<StationAvailability>>("http://localhost:8081/station_availability")

        // Then
        assertTrue(stationAvailabilityList.isNotEmpty())
        assertTrue(stationAvailabilityList.any { it.station == "Aker Brygge" })
    }
}