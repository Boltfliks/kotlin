package com.example.lab11.viewmodel



import androidx.lifecycle.ViewModel
import androidx.room.util.copy
import com.example.lab11.R
import com.example.lab11.models.Datasource
import com.example.lab11.models.Place
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class PlacesViewModel : ViewModel() {
    val datasource = Datasource()
    private val _places = MutableStateFlow<List<Place>>(datasource.loadPlaces())
    val places: StateFlow<List<Place>> = _places

    suspend fun addItem() {
        delay(1500)
        val newPlace = datasource.loadPlaces().random()
        _places.update { it + newPlace }
    }

    suspend fun deleteItem() {
        delay(1500)
        _places.update { list ->
            if (list.isEmpty()) list
            else {
                val index = Random.nextInt(list.size)
                list.filterIndexed { i, _ -> i != index }
            }
        }
    }


    suspend fun updateItem() {
        delay(1500)

        val allPlaces = datasource.loadPlaces()

        _places.update { list ->
            if (list.isEmpty()) return@update list

            val index = Random.nextInt(list.size)
            val newPlace = allPlaces.random()

            list.mapIndexed { i, place ->
                if (i == index) newPlace else place
            }
        }
    }



}