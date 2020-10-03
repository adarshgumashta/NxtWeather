package info.aneury.nxtweather

import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat


///@brief this class Help developer to implement logic for handling permission request.
///       I was thinking to make this class a child of Activity or ActivityCompact in order to
///       handle the result but by now let left as is. it should work correctly except
///       handling the result.
class NxtWeatherPermission {
    private val TAG="NxtWeatherPermission"
   private var debug = false;

    private var permission_listener: NxtWeatherPermissionListener? = null

   fun RequestPermission(activity: Activity,permission: String, requestCode: Int){
       if(ActivityCompat.checkSelfPermission(activity,permission)==PackageManager.PERMISSION_GRANTED)
       {
           if(debug)Log.d(TAG,"Permission is granted nothing to do.")
           return
       }
       else
       {
           if(debug)Log.d(TAG,"Permission has to be requested, in any case of Shl..")
           if(ActivityCompat.shouldShowRequestPermissionRationale(activity, permission))
           {
               ////todo Add the logic to feed back the user about the needs of accept permission.
               ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
           }
           else
           {
               ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
           }
       }
       return
   }

   fun setPermissionListener(permission_listener: NxtWeatherPermissionListener){
        this.permission_listener = permission_listener
   }

    ///todo fix this function in order to give the correct message or feedback in case of use of the
    ///     listener.
   fun handleResponse(  requestCode: Int, permissions: Array<String>, grantResults: IntArray){
      if(permission_listener != null){

      }
   }

}