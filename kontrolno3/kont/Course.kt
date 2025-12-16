package com.example.kontrolno3.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Course(
    @StringRes val nameResId: Int,
    @DrawableRes val imageResId: Int,
    val freePlaces: Int
)
