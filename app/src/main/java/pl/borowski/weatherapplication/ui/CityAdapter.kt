package pl.borowski.weatherapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.borowski.weatherapplication.R

class CityAdapter(private val cities: List<String>, private val clickListener: (String) -> Unit) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private var selectedCity: String? = null

    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityName: TextView = itemView.findViewById(R.id.city_name_text)

        fun bind(city: String, isSelected: Boolean) {
            cityName.text = city
            itemView.setOnClickListener {
                clickListener(city)
                setSelectedCity(city)
            }
            itemView.setBackgroundResource(if (isSelected) R.drawable.selected_item_background else R.drawable.default_item_background)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.city_list_item, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        val isSelected = city == selectedCity
        holder.bind(city, isSelected)
    }

    override fun getItemCount(): Int = cities.size

    fun setSelectedCity(city: String) {
        selectedCity = city
        notifyDataSetChanged()
    }
}
