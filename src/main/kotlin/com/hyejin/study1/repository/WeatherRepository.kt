package com.hyejin.study1.repository

import com.hyejin.study1.dto.WeatherDto

interface WeatherRepository {
    fun getWeather(): WeatherDto
}