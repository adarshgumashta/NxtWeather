package info.aneury.nxtweather

import android.util.Log
import info.aneury.nxtweather.model.NxtWeatherInformation
import info.aneury.nxtweather.model.NxtWeatherInformationRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class NxtWeatherNetworkService {

        val TAG = "NxtWeatherNetworkService"

        var API_KEY = "d4ac4021a5608a2a0a9af63f9a2f2705"

         var debug = false



        interface NxtWeatherNetworkServiceHandler{
            fun onSuccess(buffer :NxtWeatherInformation?)

            fun onFailure(error_msg : String)
        }

        var response_handler: NxtWeatherNetworkServiceHandler? = null



        fun consumeService(lat:Double?,lon:Double?){
            val retrofit = getRetrofitInstance()
            val consumeServer = retrofit.create(NxtWeatherInformationRetrofit::class.java)

            val call = consumeServer.getWeather(lat, lon, API_KEY)

            call.enqueue(object : Callback<NxtWeatherInformation> {
                override fun onResponse(call: Call<NxtWeatherInformation>?, response: Response<NxtWeatherInformation>?) {

                    if(response!!.isSuccessful && response_handler != null)
                    {
                        response_handler!!.onSuccess(response.body())
                    }
                    if(debug){
                        Log.d(TAG, response?.isSuccessful.toString())
                        Log.d(TAG, response?.raw().toString())
                        Log.d(TAG, response?.body().toString())
                        Log.d(TAG,response?.errorBody().toString())
                        Log.d(TAG,response?.message().toString())
                    }
                }
                override fun onFailure(call: Call<NxtWeatherInformation>?, t: Throwable?)
                {
                    response_handler!!.onFailure(t!!.message.toString())
                }
            });
        }


    companion object{
        ///by now by default with use OpenWeather.
        var BASE_URL = "https://api.openweathermap.org"

        fun getRetrofitInstance(): Retrofit {
            val retrofit :Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }

    }
}