package com.example.kontrolno3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kontrolno3.models.Course
import com.example.kontrolno3.data.Datasource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.collections.get
import kotlin.random.Random

class CoursesViewModel : ViewModel() {

    private val datasource = Datasource()
    private val _courses = MutableStateFlow<List<Course>>(datasource.loadCourses())
    val courses: StateFlow<List<Course>> = _courses

    init {
        startRandomUpdates()
    }

    fun startRandomUpdates() {
        viewModelScope.launch {
            while (true) {
                delay(Random.nextLong(1000, 3000))

                _courses.update { list ->
                    val index = Random.nextInt(list.size)
                    val course = list[index]

                    // Decrease first
                    val decreased = if (course.freePlaces == 0) 0
                    else Random.nextInt(1, course.freePlaces + 1)
                    var newPlaces = course.freePlaces - decreased

                    // Immediately reset if it reaches 0
                    if (newPlaces <= 0) {
                        newPlaces = 300
                    }

                    list.mapIndexed { i, c ->
                        if (i == index) c.copy(freePlaces = newPlaces)
                        else c
                    }
                }
            }
        }
    }

}
