package info.aneury.nxtweathertester

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        capture_dialog.setOnClickListener({
            val dialog :NxtWeatherAppCustom = NxtWeatherAppCustom(this)
            dialog.show()
        })

        capture_dialog2.setOnClickListener({
            val bundle: Bundle = Bundle()
            bundle.putBoolean("current", false)
            ///the logic of capture lat&long should be here.
        })
    }
}
