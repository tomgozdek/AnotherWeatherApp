package tgozdek.com.anotherweatherapp.domain.datasource

import tgozdek.com.anotherweatherapp.data.db.ForecastDb
import tgozdek.com.anotherweatherapp.data.server.ForecastServer
import tgozdek.com.anotherweatherapp.domain.models.ForecastList
import tgozdek.com.anotherweatherapp.extensions.firstResult

class ForecastProvider(private val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {
    companion object {
        const val DAY_IN_MILLIS = 24 * 60 * 60 * 1000
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int) : ForecastList = sources.firstResult {
        requestSource(it, days, zipCode)
    }

    private fun requestSource(source: ForecastDataSource, days: Int, zipCode: Long): ForecastList? {
        val res = source.requestForecastByZipCode(zipCode, todayTimeSpan())
        return if (res != null && res.size >= days) res else null
    }

    private fun todayTimeSpan(): Long = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS
}