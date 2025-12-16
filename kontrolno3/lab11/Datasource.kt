package com.example.lab11.models

import com.example.lab11.R

class Datasource {
    fun loadPlaces(): List<Place>{
        return listOf(
            Place(R.string.place1,R.drawable.image1),
            Place(R.string.place2,R.drawable.image2),
            Place(R.string.place3,R.drawable.image3),
            Place(R.string.place4,R.drawable.image4),
            Place(R.string.place5,R.drawable.image5),
            Place(R.string.place6,R.drawable.image6),
            Place(R.string.place7,R.drawable.image7),
            Place(R.string.place8,R.drawable.image8),
            Place(R.string.place9,R.drawable.image9),
            Place(R.string.place10,R.drawable.image10)
        )
    }
}