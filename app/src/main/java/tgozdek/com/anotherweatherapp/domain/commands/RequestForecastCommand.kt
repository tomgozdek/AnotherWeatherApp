package tgozdek.com.anotherweatherapp.domain.commands

import tgozdek.com.anotherweatherapp.data.ForecastRequest
import tgozdek.com.anotherweatherapp.domain.ForecastDataMapper
import tgozdek.com.anotherweatherapp.domain.models.ForecastList

class RequestForecastCommand(private val cityZip : Long) : Command<ForecastList>{
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(cityZip)
        return ForecastDataMapper().convertFromDataModel(cityZip, forecastRequest.execute())
    }

}