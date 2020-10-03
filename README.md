# NxtWeather

is a library that would help to build application that depends on weather information in a easier way this library
would handle Permission, Location and API request is a fashion manner.

### to add the library to your project:

in your root module add this repository
<pre>
    maven url{'http://jitpack.io'}
</pre>

add in module gradle this dependecy as here:
<pre>
  <code>
    implementation 'com.github.aneury1:NxtWeather:nA01'
  </code>
</pre>

### in order to request Permission use this code
<pre>
  <code>
   permission.RequestPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION, COARSED_LOC)
 </code>
</pre>

### in order to request Location use this code snipped
<pre>
  <code>
    var weatherLoc : NxtWeatherLocation = NxtWeatherLocation(this).setLocationListener(
                object: NxtWeatherLocation.NxtWeatherLocationListener{
                    override fun onErrorOfLocation(message: String?){
                        Log.d("errror", message!!)
                    }
                    override fun setLocation(location: Location?) =Unit
                    override fun setLocationParam(longi: Double?, latitude: Double?) {
                      
                      /// Do your stuff..!!
                     
                    }
                }
 </code>
</pre>

#### in order to consume the Service data you shall use NxtWeatherNetworkService and implements NxtWeatherNetworkService.NxtWeatherNetworkServiceHandler with setListener or response_handler variable.

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

 ## TODO
 there are a plenty of work to do:
 - open classes in order to use other Services
 - add need set & get function
 - add exception handling
 - add location Response Handler
 - ...
        