package pl.borowski.weatherapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import pl.borowski.weatherapplication.R
import pl.borowski.weatherapplication.databinding.FragmentCityListBinding
import pl.borowski.weatherapplication.viewmodel.SharedViewModel

class CityListFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel
    private var _binding: FragmentCityListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val cities = listOf("Gdańsk", "Sopot", "Gdynia")
        val adapter = CityAdapter(cities) { selectedCity ->
            sharedViewModel.selectCity(selectedCity)
        }

        binding.cityRecyclerView.adapter = adapter

        // Obserwuj zmiany w wybranym mieście
        sharedViewModel.selectedCity.observe(viewLifecycleOwner, { city ->
            adapter.setSelectedCity(city)
            updateCityInfo(city)
        })
    }

    private fun updateCityInfo(city: String) {
        val infoText = when (city) {
            "Gdańsk" -> getString(R.string.city_gdansk_info)
            "Sopot" -> getString(R.string.city_sopot_info)
            "Gdynia" -> getString(R.string.city_gdynia_info)
            else -> ""
        }
        binding.cityInfoText.text = infoText
        binding.cityInfoText.visibility = if (infoText.isNotEmpty()) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
