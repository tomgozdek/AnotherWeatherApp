package tgozdek.com.anotherweatherapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import tgozdek.com.anotherweatherapp.R
import tgozdek.com.anotherweatherapp.domain.models.Forecast
import tgozdek.com.anotherweatherapp.domain.models.ForecastList
import tgozdek.com.anotherweatherapp.ui.utils.ctx

class ForecastListAdapter(private val weekForecast: ForecastList, private val itemClick: ForecastListAdapter.OnItemClickListener) : RecyclerView
.Adapter<ForecastListAdapter.ViewHolder>() {

    interface OnItemClickListener{
        operator fun invoke(forecast: Forecast)
    }

    override fun getItemCount(): Int = weekForecast.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindForecast(weekForecast[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick);

    }

    class ViewHolder(view : View, private val itemClick: OnItemClickListener) : RecyclerView.ViewHolder(view){
        private val iconView = view.find<ImageView>(R.id.icon)
        private val dateView = view.find<TextView>(R.id.date)
        private val descriptionView = view.find<TextView>(R.id.description)
        private val minTemperatureView = view.find<TextView>(R.id.minTemperature)
        private val maxTemperatureView = view.find<TextView>(R.id.maxTemperature)

        fun bindForecast(forecast: Forecast) = with(forecast){
            Picasso.with(itemView.ctx).load(iconUrl).into(iconView)
            dateView.text = date
            descriptionView.text = description
            minTemperatureView.text = low.toString()
            maxTemperatureView.text = high.toString()
            itemView.setOnClickListener {
                itemClick(this)
            }
        }
    }
}