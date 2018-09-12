package tgozdek.com.anotherweatherapp.data.db

import tgozdek.com.anotherweatherapp.domain.models.Forecast
import tgozdek.com.anotherweatherapp.domain.models.ForecastList

class DbDataMapper {
    fun convertToDomain(forecast: CityForecast) = with(forecast){
        val daily = dailyForecast.map { convertToDomain(it) }
    }

    private fun convertToDomain(dayForecast: DayForecast) = with(dayForecast){
        Forecast(date, description, high, low, iconUrl)
    }

    fun convertFromDomain(forecast: ForecastList) = with(forecast){
        val daily = dailyForecast.map { convertFromDomain(id, it) }
        CityForecast(id, city, country, daily);
    }

    fun convertFromDomain(cityId : Long, forecast : Forecast) = with(forecast){
        DayForecast(date, description, high, low, iconUrl, cityId)
    }
}