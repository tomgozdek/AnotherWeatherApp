package tgozdek.com.anotherweatherapp.data.server

import tgozdek.com.anotherweatherapp.data.db.ForecastDb
import tgozdek.com.anotherweatherapp.domain.datasource.ForecastDataSource
import tgozdek.com.anotherweatherapp.domain.models.ForecastList

class ForecastServer(private val dataMapper: ServerDataMapper = ServerDataMapper(),
                     private val forecastDb : ForecastDb = ForecastDb()) : ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }
}