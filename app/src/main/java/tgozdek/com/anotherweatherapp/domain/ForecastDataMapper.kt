package tgozdek.com.anotherweatherapp.domain

import tgozdek.com.anotherweatherapp.data.Forecast
import tgozdek.com.anotherweatherapp.data.ForecastResult
import tgozdek.com.anotherweatherapp.domain.models.Forecast as ModelForecast
import tgozdek.com.anotherweatherapp.domain.models.ForecastList
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ForecastDataMapper {
    fun convertFromDataModel(forecast: ForecastResult) : ForecastList {
        return ForecastList(forecast.city.name, forecast.city.country, convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.mapIndexed { index, forecast ->
            val dateTime = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(index.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dateTime))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(dateToString(forecast.dt), forecast.weather[0].description, forecast.temp.max.toInt(),
                forecast.temp.min.toInt())
    }

    private fun dateToString(date: Long): String {
        val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return dateFormat.format(date);
    }
}