package com.juanca.exploreit.application

import android.app.Application
import android.content.Context
import com.juanca.exploreit.data.local.AppDataBase

class App: Application() {

    lateinit var database: AppDataBase

    companion object{
        private var instance: App? = null

        fun context() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        database = AppDataBase.getInstanceDB(this)

    }
}