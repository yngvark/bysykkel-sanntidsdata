package com.yngvark.sanntid.join_data

data class StationAvailability(
        val station:String,
        val numBikesAvailable:Int,
        val numDocksAvailable:Int
)