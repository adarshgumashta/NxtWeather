package info.aneury.nxtweather.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NxtWeatherInformationRetrofit {
    //lat={lat}&lon={lon}&cnt={cnt}&appid={appid}
    @GET("/data/2.5/weather?")
    fun getWeather(@Query("lat")lan:Double?,
                   @Query("lon")lon:Double?,
                   @Query("APPID")key:Any,

                   ): Call<NxtWeatherInformation>

    @GET("data/2.5/weather?")
    fun getCurrentWeatherData(@Query("lat") lat: String, @Query("lon") lon: String, @Query("APPID") app_id: String): Call<NxtWeatherInformation>
}