package pl.borowski.weatherapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import pl.borowski.weatherapplication.databinding.FragmentWeatherBinding
import pl.borowski.weatherapplication.viewmodel.WeatherViewModel

class WeatherFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var binding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        val city = "Gdańsk" // Można zmienić na wybrane miasto
        val apiKey = "26bd3d5d8061cdd76d6b043c7c58eca5"

        viewModel.getWeather(city, apiKey).observe(viewLifecycleOwner, { weather ->
            binding.cityName.text = weather.name
            binding.temperature.text = "${weather.main.temp}°C"
            binding.weatherDescription.text = weather.weather[0].description
            // Ładowanie ikony pogody za pomocą Glide lub Picasso
        })
    }
}
