package com.yngvark.sanntid

import com.yngvark.sanntid.expose_joined_data.StationAvailabilityStore
import com.yngvark.sanntid.gather_data.BysykkelApi
import com.yngvark.sanntid.join_data.BysykkelStationJoiner
import com.yngvark.sanntid.show_joined_data.ConsolePresenter

class ApplicationRunner(
        private val bysykkelApi: BysykkelApi,
        private val bysykkelJoiner: BysykkelStationJoiner,
        private val consolePresenter: ConsolePresenter,
        private val stationAvailabilityStore: StationAvailabilityStore
) {
    fun run() {
        val stationInformation = bysykkelApi.getStationInformation()
        val stationStatus = bysykkelApi.getStationStatus()

        val stationAvailability = bysykkelJoiner.join(stationInformation, stationStatus)

        // Store data for it to be available by REST-endpoint
        stationAvailabilityStore.stationAvailability = stationAvailability

        consolePresenter.show(stationAvailability)
    }
}