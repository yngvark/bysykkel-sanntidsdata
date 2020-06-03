package com.yngvark.sanntid.gather_data

import com.yngvark.sanntid.gather_data.models.station_information.StationInformation
import com.yngvark.sanntid.gather_data.models.station_status.StationStatus

class BysykkelApiImpl(
        private val httpUtil: HttpUtil
) : BysykkelApi {
    override fun getStationInformation(): StationInformation {
        return httpUtil.get<StationInformation>("https://gbfs.urbansharing.com/oslobysykkel.no/station_information.json")
    }

    override fun getStationStatus(): StationStatus {
        return httpUtil.get<StationStatus>("https://gbfs.urbansharing.com/oslobysykkel.no/station_status.json")
    }
}
