package com.yngvark.sanntid.show_joined_data

class OutputPrinterMock(
        private val stringBuilder:StringBuilder = java.lang.StringBuilder()
) : OutputPrinter
{
    override fun print(text: Any) {
        stringBuilder.append(text)
    }

    override fun println() {
        stringBuilder.appendln()
    }

    override fun println(text: Any) {
        stringBuilder.appendln(text)
    }

    fun getOutput():String {
        return stringBuilder.toString()
    }
}
