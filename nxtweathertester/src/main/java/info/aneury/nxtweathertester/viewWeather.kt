package info.aneury.nxtweathertester

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import info.aneury.nxtweather.NxtWeatherNetworkService
import info.aneury.nxtweather.model.NxtWeatherInformation


class viewWeather : AppCompatActivity()
    {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_weather)
        var bundle:Bundle? = intent.extras
        if(bundle != null){

        }

        val service: NxtWeatherNetworkService = NxtWeatherNetworkService()
        service.response_handler= (object:
            NxtWeatherNetworkService.NxtWeatherNetworkServiceHandler {
            override fun onFailure(error_msg: String) {
               Log.d("cons", error_msg)
            }

            override fun onSuccess(buffer: NxtWeatherInformation?) {
                Log.d("cons", "Message"+buffer!!.sys.country)
            }

        })
        service.consumeService(3455.56, 45656.56)
    }
}