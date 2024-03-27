package feature

import android.app.Application
import feature.data.source.local.AppDatabase

class App : Application (){

    override fun onCreate() {
        super.onCreate()
        AppDatabase.getInstance(this)
    }
}