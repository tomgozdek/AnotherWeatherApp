package tgozdek.com.anotherweatherapp.extensions

import android.content.Context
import android.widget.Toast

fun Context.myToast(message: CharSequence, duration: Int = Toast.LENGTH_LONG) = Toast.makeText(this, message, duration)
