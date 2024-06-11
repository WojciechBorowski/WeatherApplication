package pl.borowski.weatherapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pl.borowski.weatherapplication.data.local.AppDatabase
import pl.borowski.weatherapplication.data.local.City
import pl.borowski.weatherapplication.data.remote.RetrofitInstance
import pl.borowski.weatherapplication.data.remote.WeatherResponse
import pl.borowski.weatherapplication.repository.WeatherRepository

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: WeatherRepository

    init {
        val cityDao = AppDatabase.getDatabase(application).cityDao()
        repository = WeatherRepository(RetrofitInstance.api, cityDao)
    }

    fun getWeather(city: String, apiKey: String): LiveData<WeatherResponse> {
        return repository.getWeather(city, apiKey)
    }

    fun getCities(): LiveData<List<City>> {
        return repository.getCities()
    }

    fun addCity(city: City) {
        repository.addCity(city)
    }
}
