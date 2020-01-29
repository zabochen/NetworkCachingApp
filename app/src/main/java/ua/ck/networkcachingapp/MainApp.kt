package ua.ck.networkcachingapp

import android.app.Application
import com.facebook.stetho.Stetho

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initStetho()
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }
}