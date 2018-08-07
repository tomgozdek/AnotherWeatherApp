package tgozdek.com.anotherweatherapp.domain

import tgozdek.com.anotherweatherapp.data.Forecast
import tgozdek.com.anotherweatherapp.data.ForecastResult
import tgozdek.com.anotherweatherapp.domain.models.ForecastList
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import tgozdek.com.anotherweatherapp.domain.models.Forecast as ModelForecast

class ForecastDataMapper {
    fun convertFromDataModel(forecast: ForecastResult) : ForecastList =
            ForecastList(forecast.city.name, forecast.city.country, convertForecastListToDomain(forecast.list))

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> = list.mapIndexed { index, forecast ->
        val dateTime = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(index.toLong())
        convertForecastItemToDomain(forecast.copy(dt = dateTime))
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast =
            ModelForecast(dateToString(forecast.dt), forecast.weather[0].description, forecast.temp.max.toInt(),
                    forecast.temp.min.toInt(), generateIconUrl(forecast.weather[0].icon))

    private fun generateIconUrl(iconCode: String): String =
            "http://openweathermap.org/img/w/$iconCode.png"

    private fun dateToString(date: Long): String {
        val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return dateFormat.format(date);
    }
}