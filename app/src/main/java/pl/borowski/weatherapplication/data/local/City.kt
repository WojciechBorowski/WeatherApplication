package pl.borowski.weatherapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class City(
    @PrimaryKey val name: String
)
