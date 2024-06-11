package pl.borowski.weatherapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import pl.borowski.weatherapplication.databinding.FragmentCityListBinding
import pl.borowski.weatherapplication.viewmodel.WeatherViewModel

class CityListFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel
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
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        // Obsługa listy miast
        val cityListView = binding.cityListView

        // Przykładowe dane do listy
        val cities = listOf("Gdańsk", "Sopot", "Gdynia")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, cities)
        cityListView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}