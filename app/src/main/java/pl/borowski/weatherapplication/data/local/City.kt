package pl.borowski.weatherapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_table")
data class City(
    @PrimaryKey val name: String
)
