package com.yngvark.sanntid.gather_data.models.station_information

import com.google.gson.annotations.SerializedName

data class StationInformationStation(
    @SerializedName("station_id")
    val stationId:Long,

    val name:String
)
