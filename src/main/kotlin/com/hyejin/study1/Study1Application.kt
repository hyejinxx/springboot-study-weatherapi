package com.hyejin.study1

import com.hyejin.study1.controller.WeatherController
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Study1Application

fun main(args: Array<String>) {
	runApplication<Study1Application>(*args)
	WeatherController().getWeather()

}
