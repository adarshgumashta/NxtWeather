package info.aneury.nxtweathertester

import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import info.aneury.nxtweather.NxtWeatherLocation
import info.aneury.nxtweather.NxtWeatherPermission

import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest


class MainActivity : AppCompatActivity() {

    val FINE_LOC = 1
    val COARSED_LOC = 2

    var permission : NxtWeatherPermission = NxtWeatherPermission()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        permission.RequestPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION, COARSED_LOC)

        capture_dialog.setOnClickListener({
            val dialog :NxtWeatherAppCustom = NxtWeatherAppCustom(this)
            dialog.show()
        })

        capture_dialog2.setOnClickListener({
            val bundle: Bundle = Bundle()
            permission.RequestPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION, FINE_LOC)
            bundle.putBoolean("current", true)

            var weatherLoc : NxtWeatherLocation = NxtWeatherLocation(this).setLocationListener(
                object: NxtWeatherLocation.NxtWeatherLocationListener{
                    override fun onErrorOfLocation(message: String?){
                        Log.d("errror", message!!)
                    }
                    override fun setLocation(location: Location?) =Unit
                    override fun setLocationParam(longi: Double?, latitude: Double?) {
                        if (longi != null) {
                            bundle.putDouble("Longitude", longi)
                        }
                        if (latitude != null) {
                            bundle.putDouble("Latitude", latitude)
                        }
                        val intent : Intent= Intent(applicationContext, viewWeather::class.java)
                        intent.putExtra("bundle", bundle)
                        startActivity(intent)
                    }
                }
            )

            weatherLoc.getLocation()
            ///the logic of capture lat&long should be here.
        })

    }
}
