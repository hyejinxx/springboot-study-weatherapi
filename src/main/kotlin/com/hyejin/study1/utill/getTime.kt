package com.hyejin.study1.utill

fun getTime(h: String, m: String): String {
    var result = ""

    result = if (m.toInt() < 45) {
        // 0시면 2330
        if (h == "00") "2330"
        // 아니면 1시간 전 날씨 정보 부르기
        else {
            val resultH = h.toInt() - 1
            // 1자리면 0 붙여서 2자리로 만들기
            if (resultH < 10) "0" + resultH + "30"
            // 2자리면 그대로
            else resultH.toString() + "30"
        }
    }
    // 45분 이후면 바로 정보 받아오기
    else h + "30"

    return result
}