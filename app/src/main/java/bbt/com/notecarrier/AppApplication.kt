package bbt.com.notecarrier

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}