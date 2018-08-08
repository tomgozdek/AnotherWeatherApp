package tgozdek.com.anotherweatherapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*
import tgozdek.com.anotherweatherapp.R
import tgozdek.com.anotherweatherapp.domain.models.Forecast
import tgozdek.com.anotherweatherapp.domain.models.ForecastList
import tgozdek.com.anotherweatherapp.ui.utils.ctx

class ForecastListAdapter(private val weekForecast: ForecastList, private val itemClick: (Forecast) -> Unit) : RecyclerView
.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun getItemCount(): Int = weekForecast.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindForecast(weekForecast[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick);

    }

    class ViewHolder(view : View, private val itemClick: (Forecast) -> Unit) : RecyclerView.ViewHolder(view){

        fun bindForecast(forecast: Forecast) = with(forecast){
            Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
            itemView.date.text = date
            itemView.description.text = description
            itemView.minTemperature.text = "${low}º"
            itemView.maxTemperature.text = "${high}º"
            itemView.setOnClickListener {
                itemClick(this)
            }
        }
    }
}