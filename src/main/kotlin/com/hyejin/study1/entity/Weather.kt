package com.hyejin.study1.entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.Getter
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
data class Weather(
        val response : Response
) {

    data class Response(
            val header: Header,
            val body: Body
    )
    data class Header(
            val resultCode: Int,
            val resultMsg: String
    )
    data class Body(
            val dataType: String,
            val items: Items,
            val totalCount: Int,
            val pageNo: Int,
            val numOfRows: Int

    )

    data class Items(
            val item: ArrayList<Item>
    )

    data class Item(
            val category: String,
            val baseDate: String,
            val baseTime: String,
            val fcstValue: String,
            val fcstDate: String,
            val fcstTime: String,
            val nx: String,
            val ny: String

    )
}
