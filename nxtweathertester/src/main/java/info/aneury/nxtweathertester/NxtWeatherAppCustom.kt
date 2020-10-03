package info.aneury.nxtweathertester


import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.dialog.*


class NxtWeatherAppCustom(var c: Activity) : Dialog(c) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog)
        val l = findViewById<Button>(R.id.backbutton)
        lat.selectAll()
        longi.selectAll()

        backbutton.setOnClickListener({
            this.dismiss()
        })

        search.setOnClickListener({

            try {
                ///Toast.makeText(c.applicationContext,"you need to set Latitude value", Toast.LENGTH_LONG).show()
                /// val snack = Snackbar.make(l,"Value of Latitude and Longi must be set",Snackbar.LENGTH_LONG)
                //// snack.show()

                val bundle: Bundle = Bundle()
                bundle.putBoolean("current", false)
                bundle.putDouble("Latitude", lat.text.toString().toDouble())
                bundle.putDouble("Longitude", longi.text.toString().toDouble())

                val intent = Intent(c.applicationContext, viewWeather::class.java)
                intent.putExtra("bundle", bundle)
                c.startActivity(intent)
                this.dismiss()
            }catch (exception: Exception){
                 val snack = Snackbar.make(l,"Value of Latitude and Longi must be set",Snackbar.LENGTH_LONG)
                 snack.show()
            }

        })
    }
}