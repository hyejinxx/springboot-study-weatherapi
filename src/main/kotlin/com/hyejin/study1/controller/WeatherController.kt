package com.hyejin.study1.controller

import com.hyejin.study1.dto.WeatherDto
import com.hyejin.study1.service.WeatherService
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController


@RestController
@RequiredArgsConstructor
class WeatherController {
    val weatherService = WeatherService()

    fun getWeather(): ResponseEntity<WeatherDto> {
        var weather: WeatherDto? = null
        try {
            weather = weatherService.getWeather()
            println(weather.toString())
        }catch (e: Exception){
            e.printStackTrace()
        }
        return ResponseEntity.ok(weather)
    }
}