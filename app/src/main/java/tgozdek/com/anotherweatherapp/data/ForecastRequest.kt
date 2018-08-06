package tgozdek.com.anotherweatherapp.data

import android.util.Log
import com.google.gson.Gson
import java.net.URL

class ForecastRequest(private val zipCode : String){

    companion object {
        private const val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private const val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private const val COMPLETE_URL = "$URL&APPID=$APP_ID&zip="

    }
    fun execute() : ForecastResult {
        val forecastResponseJson = URL(COMPLETE_URL+zipCode).readText()
        return Gson().fromJson(forecastResponseJson,ForecastResult::class.java)
    }
}
