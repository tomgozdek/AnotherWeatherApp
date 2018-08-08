package tgozdek.com.anotherweatherapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import tgozdek.com.anotherweatherapp.R
import tgozdek.com.anotherweatherapp.domain.commands.RequestForecastCommand
import tgozdek.com.anotherweatherapp.ui.adapters.ForecastListAdapter

class MainActivity : AppCompatActivity() {


    private val items = listOf(
        "Mon 6/23 - Sunny - 31/17",
        "Tue 6/24 - Foggy - 21/8",
        "Wed 6/25 - Cloudy - 22/17",
        "Thurs 6/26 - Rainy - 18/11",
        "Fri 6/27 - Foggy - 21/10",
        "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
        "Sun 6/29 - Sunny - 20/7")

    val url = "http://api.openweathermap.org/data/2.5/forecast/daily?" +"APPID=15646a06818f61f7b8d7823ca833e1ce&q=94043&mode=json&units=metric&cnt=7"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastList.layoutManager = LinearLayoutManager(this)
        showWeatherInfo(forecastList)
    }

    private fun showWeatherInfo(forecastList: RecyclerView) {
        doAsync {
            val forecastCommandResponse = RequestForecastCommand("Warsaw,pl").execute()
            uiThread {
                forecastList.adapter = ForecastListAdapter(forecastCommandResponse, {toast(it.date)})
            }
        }
    }

}
