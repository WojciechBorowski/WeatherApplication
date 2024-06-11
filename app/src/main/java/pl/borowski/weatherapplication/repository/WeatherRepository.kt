package pl.borowski.weatherapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.borowski.weatherapplication.data.remote.WeatherApi
import pl.borowski.weatherapplication.data.remote.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository(private val weatherApi: WeatherApi) {

    fun getWeather(city: String, apiKey: String): LiveData<WeatherResponse> {
        val data = MutableLiveData<WeatherResponse>()
        weatherApi.getWeather(city, apiKey).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                // Handle failure
            }
        })
        return data
    }
}
