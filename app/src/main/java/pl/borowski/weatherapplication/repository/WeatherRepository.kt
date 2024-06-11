package pl.borowski.weatherapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pl.borowski.weatherapplication.data.local.City
import pl.borowski.weatherapplication.data.local.CityDao
import pl.borowski.weatherapplication.data.remote.WeatherApi
import pl.borowski.weatherapplication.data.remote.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository(private val api: WeatherApi, private val cityDao: CityDao) {
    fun getWeather(city: String, apiKey: String): LiveData<WeatherResponse> {
        val data = MutableLiveData<WeatherResponse>()
        api.getWeather(city, apiKey).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }
            }
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                // Handle failure
            }
        })
        return data
    }

    fun getCities(): LiveData<List<City>> {
        return MutableLiveData(cityDao.getAll())
    }

    fun addCity(city: City) {
        cityDao.insertAll(city)
    }
}
