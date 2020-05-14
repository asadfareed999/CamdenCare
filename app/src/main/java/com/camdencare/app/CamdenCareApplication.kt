package com.camdencare.app

import android.app.Application
import com.facebook.stetho.Stetho

class CamdenCareApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //init stetho
        Stetho.initializeWithDefaults(this)
    }
}