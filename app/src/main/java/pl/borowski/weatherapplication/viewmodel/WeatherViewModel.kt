package pl.borowski.weatherapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import pl.borowski.weatherapplication.data.remote.RetrofitInstance
import pl.borowski.weatherapplication.data.remote.WeatherResponse
import pl.borowski.weatherapplication.repository.WeatherRepository

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: WeatherRepository

    init {
        repository = WeatherRepository(RetrofitInstance.api)
    }

    fun getWeather(city: String, apiKey: String): LiveData<WeatherResponse> {
        return repository.getWeather(city, apiKey)
    }
}
