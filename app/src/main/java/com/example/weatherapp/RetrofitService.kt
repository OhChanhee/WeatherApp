package com.example.weatherapp

import com.example.weatherapp.data.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("data/2.5/weather")
    fun getWeatherData(
        @Query("q") cityname:String,
        @Query("appid") apikey:String
    ): Call<WeatherData>
}