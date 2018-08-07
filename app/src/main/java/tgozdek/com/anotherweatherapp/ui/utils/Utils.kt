package tgozdek.com.anotherweatherapp.ui.utils

import android.content.Context
import android.view.View
import android.widget.Toast

fun Context.myToast(message: CharSequence, duration: Int = Toast.LENGTH_LONG) = Toast.makeText(this, message, duration)

val View.ctx: Context
    get() = context

