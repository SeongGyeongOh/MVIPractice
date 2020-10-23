package com.osg.mvipractice.intent

sealed class MainIntent{
    //object : 클래스를 정의하는 키워드. 싱글톤(Singleton) 패턴이 적용되기 때문에 객체가 한 번만 생성됨
    object FetchUser : MainIntent()
}