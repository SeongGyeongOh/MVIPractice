package com.osg.mvipractice.data

import com.squareup.moshi.Json

//@Json 어노테이션을 이용해서 받아오는 Json 문자열의 키값을 kotlin에서 재정의 가능
//ex. Json의 키값에 공백이 있는 경우
// @Json(name="today menu") val menuoftoday: String = ""  -> today menu 라는 키값을 menuoftoday로 받아옴
data class User(
    @Json(name="id") val id: Int=0,
    @Json(name="name") val name: String = "",
    @Json(name="email") val email: String = "",
    @Json(name="avatar") val avatar: String = ""
)