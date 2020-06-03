package com.yngvark.sanntid.show_joined_data

import com.yngvark.sanntid.join_data.StationAvailability

class ConsolePresenter(
        private val outputPrinter:OutputPrinter
) {
    private val initialSpaceLengthAfterStation = 37
    private val initialSpaceLengthAfterNumBikesAvailable = 20

    fun show(data: List<StationAvailability>) {
        outputPrinter.println()

        outputPrinter.println("---------------------------------------------------------------------")
        outputPrinter.println("Stasjon                              Ledige sykler       Ledige låser")
        outputPrinter.println("---------------------------------------------------------------------")
        // Ex:                 Aker Brygge                          25                  5

        data.forEach {
            val spaces = calculateSpacesBetweenColumns(it)

            outputPrinter.print(it.station)
            outputPrinter.print(spaces.afterStation)
            outputPrinter.print(it.numBikesAvailable)
            outputPrinter.print(spaces.afterNumBikesAvailable)
            outputPrinter.print(it.numDocksAvailable)
            outputPrinter.println()
        }

        outputPrinter.println()
    }

    private fun calculateSpacesBetweenColumns(sa: StationAvailability): Spaces {
        val stationNameLength = sa.station.length
        val numBikesAvailableLength = sa.numBikesAvailable.toString().length

        return Spaces(
                afterStation = " ".repeat(initialSpaceLengthAfterStation - stationNameLength),
                afterNumBikesAvailable = " ".repeat(initialSpaceLengthAfterNumBikesAvailable - numBikesAvailableLength)
        )
    }

}

private data class Spaces(
        val afterStation:String,
        val afterNumBikesAvailable:String
)
