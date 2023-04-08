package com.nubank.urlshortener

import android.app.Application
import com.nubank.urlshortener.di.ApplicationComponent

class UrlShortenerApplication : Application() {
    lateinit var applicationComponent : ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().build()
    }

}