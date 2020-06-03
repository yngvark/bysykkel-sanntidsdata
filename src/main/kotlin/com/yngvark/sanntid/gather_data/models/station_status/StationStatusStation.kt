package com.yngvark.sanntid.gather_data.models.station_status

import com.google.gson.annotations.SerializedName

data class StationStatusStation(
    @SerializedName("station_id")
    val stationId:Long,

    @SerializedName("num_bikes_available")
    val numBikesAvailable:Int,

    @SerializedName("num_docks_available")
    val numDocksAvailable:Int
)
