package tgozdek.com.anotherweatherapp.ui

import android.app.Application
import tgozdek.com.anotherweatherapp.ui.utils.DelegatesExt

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}