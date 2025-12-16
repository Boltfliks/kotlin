package com.example.lab11.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Place(@StringRes val stringResourceId: Int, @DrawableRes val drawableResourceId: Int) {
}
