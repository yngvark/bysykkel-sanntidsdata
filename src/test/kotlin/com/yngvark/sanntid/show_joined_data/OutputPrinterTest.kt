package com.yngvark.sanntid.show_joined_data

import com.yngvark.sanntid.ApplicationRunner
import com.yngvark.sanntid.expose_joined_data.StationAvailabilityStore
import com.yngvark.sanntid.gather_data.models.station_information.StationInformation
import com.yngvark.sanntid.gather_data.models.station_information.StationInformationData
import com.yngvark.sanntid.gather_data.models.station_information.StationInformationStation
import com.yngvark.sanntid.gather_data.models.station_status.StationStatus
import com.yngvark.sanntid.gather_data.models.station_status.StationStatusData
import com.yngvark.sanntid.gather_data.models.station_status.StationStatusStation
import com.yngvark.sanntid.join_data.BysykkelStationJoiner
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OutputPrinterTest {
    @Test
    fun `application should show expected output`() {
        // Given
        val outputPrinterMock = OutputPrinterMock()

        val appRunner = ApplicationRunner(
                BysykkelApiMock(
                        getStationInformation(),
                        getStationStatus()
                ),
                BysykkelStationJoiner(),
                ConsolePresenter(outputPrinterMock),
                StationAvailabilityStore()
        )

        // When
        appRunner.run()

        // Then
        val expected =
            "\n" +
            "---------------------------------------------------------------------\n" +
            "Stasjon                              Ledige sykler       Ledige låser\n" +
            "---------------------------------------------------------------------\n" +
            "Galgeberg Corner                     1000                1\n" +
            "King's Cross                         5                   15\n" +
            "Sesam stasjon                        30                  150\n" +
            "\n"

        assertEquals(expected, outputPrinterMock.getOutput())
    }

    private fun getStationInformation(): StationInformation {
        return StationInformation(
                StationInformationData(
                        listOf(
                                StationInformationStation(123L, "Sesam stasjon"),
                                StationInformationStation(6L, "King's Cross"),
                                StationInformationStation(99L, "Galgeberg Corner")
                        )
                )
        )
    }

    private fun getStationStatus(): StationStatus {
        return StationStatus(
                StationStatusData(
                        listOf(
                                StationStatusStation(6L, 5, 15),
                                StationStatusStation(99L, 1000, 1),
                                StationStatusStation(123L, 30, 150)
                        )
                )
        )
    }
}
