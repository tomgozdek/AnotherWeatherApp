package tgozdek.com.anotherweatherapp.data.db

import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import tgozdek.com.anotherweatherapp.domain.models.ForecastList
import tgozdek.com.anotherweatherapp.extensions.clear
import tgozdek.com.anotherweatherapp.extensions.parseList
import tgozdek.com.anotherweatherapp.extensions.parseOpt
import tgozdek.com.anotherweatherapp.extensions.toVarargArray

class ForecastDb (private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                  private val dataMapper : DbDataMapper = DbDataMapper()){


    fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList{DayForecast(HashMap(it))}

        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt{CityForecast(HashMap(it), dailyForecast)}

        if(city != null){
            dataMapper.convertToDomain(city)
        } else {
            null
        }
    }

    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        clear(DayForecastTable.NAME)
        clear(CityForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)){
            insert(CityForecastTable.NAME, *map.toVarargArray())

            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }
    }
}