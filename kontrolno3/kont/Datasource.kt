package com.example.kontrolno3.data

import com.example.kontrolno3.models.Course
import com.example.kontrolno3.R

class Datasource {
    fun loadCourses(): List<Course> {
        return listOf(
            Course(R.string.place1, R.drawable.image1, 300),
            Course(R.string.place2, R.drawable.image2, 300),
            Course(R.string.place3, R.drawable.image3, 300),
            Course(R.string.place4, R.drawable.image4, 300),
            Course(R.string.place5, R.drawable.image5, 300),
            Course(R.string.place6, R.drawable.image6, 300)
        )
    }
}
