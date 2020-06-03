package com.yngvark.sanntid.gather_data

import com.yngvark.sanntid.gather_data.models.station_information.StationInformation
import com.yngvark.sanntid.gather_data.models.station_status.StationStatus

interface BysykkelApi {
    fun getStationInformation(): StationInformation
    fun getStationStatus(): StationStatus
}
