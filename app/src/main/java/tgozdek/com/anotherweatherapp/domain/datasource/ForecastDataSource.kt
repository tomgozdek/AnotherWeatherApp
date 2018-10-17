package tgozdek.com.anotherweatherapp.domain.datasource

import tgozdek.com.anotherweatherapp.domain.models.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long) : ForecastList?
}