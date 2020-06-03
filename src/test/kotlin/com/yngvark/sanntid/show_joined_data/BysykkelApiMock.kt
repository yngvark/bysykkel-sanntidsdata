package com.yngvark.sanntid.show_joined_data

import com.yngvark.sanntid.gather_data.BysykkelApi
import com.yngvark.sanntid.gather_data.models.station_information.StationInformation
import com.yngvark.sanntid.gather_data.models.station_status.StationStatus

class BysykkelApiMock(
        private val stationInformation: StationInformation,
        private val stationStatus: StationStatus
) : BysykkelApi {
    override fun getStationInformation(): StationInformation {
        return stationInformation
    }

    override fun getStationStatus(): StationStatus {
        return stationStatus
    }

}
