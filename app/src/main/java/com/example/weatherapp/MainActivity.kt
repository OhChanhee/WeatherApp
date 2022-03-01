package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.weatherapp.data.WeatherData
import com.example.weatherapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val cityName = "seoul"
        val apiKey = "544fb35ff7f2c48fb57b52d464f4362b"

        val retrofit = Retrofit.Builder().baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(RetrofitService::class.java)

        val resultWeather = service.getWeatherData(cityName,apiKey)

        resultWeather.enqueue(object : Callback<WeatherData>
        {
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                if(response.isSuccessful)
                {
                    binding.cityName.setText(response.body()?.name)
                    binding.curTemp.setText(response.body()?.main?.temp.toString())
                    binding.maxTemp.setText(response.body()?.main?.temp_max.toString())
                    binding.minTemp.setText(response.body()?.main?.temp_min.toString())
                }
                else
                {

                }
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {

            }

        })

    }
}