package info.aneury.nxtweather

import android.annotation.SuppressLint
import android.app.Activity
import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class NxtWeatherLocation(activity: Activity) {

    val TAG = "NxtWeatherLocation"

    var debug = false

    var _activity = activity

    interface NxtWeatherLocationListener{

        fun setLocationParam(longi: Double?, latitude :Double?)

        fun setLocation(location: Location?)

        fun onErrorOfLocation(message: String?)

    }

    var location_listener : NxtWeatherLocationListener ? = null

    fun setLocationListener(location_listener : NxtWeatherLocationListener):NxtWeatherLocation{
        this.location_listener = location_listener
        return this
    }
    @SuppressLint("MissingPermission")
    fun getLocation(){
        var fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(_activity)

            fusedLocationClient.lastLocation
                .addOnCompleteListener { taskLocation ->
                    if (taskLocation.isSuccessful && taskLocation.result != null) {

                        val location = taskLocation.result
                        if(location_listener!=null){
                            location_listener!!.setLocation(location)
                            location_listener!!.setLocationParam(location!!.longitude, location!!.latitude)
                        }
                    }
                    else
                    {
                        location_listener?.onErrorOfLocation(taskLocation.exception.toString())
                       if(debug)Log.w(TAG, "getLastLocation:exception", taskLocation.exception)
                    }
                }
    }

}