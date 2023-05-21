package com.hyejin.study1.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.hyejin.okingweather.data.Date
import com.hyejin.study1.dto.WeatherDto
import com.hyejin.study1.entity.Weather
import com.hyejin.study1.utill.getTime
import org.springframework.stereotype.Service
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat

@Service
class WeatherService {
    fun getWeather(): WeatherDto {

        val date = System.currentTimeMillis().let { current ->
            Date(
                    SimpleDateFormat("yyyyMMdd").format(current),
                    SimpleDateFormat("HH").format(current),
                    SimpleDateFormat("mm").format(current)
            )
        }
        val baseDate = date.date
        val baseTime: String = getTime(date.timeH, date.timeH)

        val servicekey = "G77F3bZzXJWT%2B%2BAQafXcdQiaLmn2RlsZb9szmlWWCRJqPLN9O1U6w7tU4ywNe%2BxNBw59Jv3wOhKQh6hjZfr6yA%3D%3D"
        val apiUrl: String = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst"

        val urlBuilder = StringBuilder(apiUrl)
        urlBuilder.append("?serviceKey=${servicekey}")
        urlBuilder.append("&pageNo=1")
        urlBuilder.append("&numOfRows=10")
        urlBuilder.append("&dataType=JSON")
        urlBuilder.append("&base_date=${baseDate}")
        urlBuilder.append("&base_time=${baseTime}")
        urlBuilder.append("&nx=55")
        urlBuilder.append("&ny=127")

        val url = URL(urlBuilder.toString())
        val urlConnection = url.openConnection() as HttpURLConnection
        urlConnection.requestMethod = "GET"
        urlConnection.setRequestProperty("Content-type", "application/json")

        val responseCode = urlConnection.responseCode
        val response = StringBuffer()
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val inputLine = urlConnection.inputStream.bufferedReader()
            inputLine.use {
                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
            }

        } else {
            println("GET NOT WORKED")
        }
        urlConnection.disconnect()
        println(response.toString())
        val result = response.toString()
        val weather = ObjectMapper().readValue(result, Weather::class.java)
        return WeatherDto(weather.response.body.items.item[0].fcstValue, weather.response.body.items.item[1].fcstValue, weather.response.body.items.item[2].fcstValue, weather.response.body.items.item[3].fcstValue)

    }


}