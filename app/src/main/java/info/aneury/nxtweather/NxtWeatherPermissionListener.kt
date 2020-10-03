package info.aneury.nxtweather

////@brief this interface should be use where permission request's response are handle
///        by the Application.
interface NxtWeatherPermissionListener {

   fun onDenny(permission: String)

   fun onAccepted(permission: String)

}