package tgozdek.com.anotherweatherapp.domain.commands

import tgozdek.com.anotherweatherapp.domain.datasource.ForecastProvider
import tgozdek.com.anotherweatherapp.domain.models.ForecastList

class RequestForecastCommand(private val cityZip : Long,
                             private val forecastProvider: ForecastProvider = ForecastProvider()) : Command<ForecastList>{

    companion object {
        const val DAYS = 7
    }

    override fun execute(): ForecastList = forecastProvider.requestByZipCode(cityZip, DAYS)

}