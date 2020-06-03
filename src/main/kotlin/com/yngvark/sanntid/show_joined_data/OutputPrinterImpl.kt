package com.yngvark.sanntid.show_joined_data

class OutputPrinterImpl : OutputPrinter {
    override fun print(text: Any) {
        kotlin.io.print(text)
    }

    override fun println() {
        kotlin.io.println()
    }

    override fun println(text: Any) {
        kotlin.io.println(text)
    }
}