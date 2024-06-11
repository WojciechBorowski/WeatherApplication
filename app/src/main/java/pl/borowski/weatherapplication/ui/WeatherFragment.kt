package pl.borowski.weatherapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import pl.borowski.weatherapplication.databinding.FragmentWeatherBinding
import pl.borowski.weatherapplication.viewmodel.SharedViewModel
import pl.borowski.weatherapplication.viewmodel.WeatherViewModel

class WeatherFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var sharedViewModel: SharedViewModel
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
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val apiKey = "563f9c32eefc5a19d777021d2751a55f"

        // Obserwowanie zmian wybranego miasta
        sharedViewModel.selectedCity.observe(viewLifecycleOwner, Observer { city ->
            viewModel.getWeather(city, apiKey).observe(viewLifecycleOwner, { weather ->
                binding.cityName.text = weather.name
                binding.temperature.text = "${weather.main.temp}°C"
                binding.weatherDescription.text = weather.weather[0].description

                // Ładowanie ikony pogody za pomocą Glide
                val iconUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}@2x.png"
                Glide.with(this)
                    .load(iconUrl)
                    .into(binding.weatherIcon)
            })
        })
    }
}
