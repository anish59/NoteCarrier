package bbt.com.notecarrier

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionListeners()
    }

    private fun actionListeners() {
        imgCapture.setOnClickListener {
            Toast.makeText(this, "Open Camera", Toast.LENGTH_LONG).show()
        }
    }

}
