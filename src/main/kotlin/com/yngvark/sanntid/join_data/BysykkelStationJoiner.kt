package com.yngvark.sanntid.join_data

import com.yngvark.sanntid.gather_data.models.station_information.StationInformation
import com.yngvark.sanntid.gather_data.models.station_information.StationInformationStation
import com.yngvark.sanntid.gather_data.models.station_status.StationStatus

class BysykkelStationJoiner {
    fun join(stationInformation: StationInformation, stationStatus: StationStatus): List<StationAvailability> {
        // Get hash map with stations to ensure o(1) lookup time complexity
        val stationIdToStationInfo = toMap(stationInformation)

        // ... so the following map operation has time complexity o(n) - instead of for instance using a double loop
        // which would result in time complexity O(n^2)
        return stationStatus.data.stations.map {
            val stationInfo = stationIdToStationInfo[it.stationId]
            val stationName = stationInfo?.name ?: "NOT FOUND"

            StationAvailability(stationName, it.numBikesAvailable, it.numDocksAvailable)
        }.sortedBy { it.station }
    }

    private fun toMap(stationInformation: StationInformation): Map<Long, StationInformationStation> {
        // toMap uses LinkedHashMap as implementation
        return stationInformation.data.stations.map { it.stationId to it }.toMap()
    }
}