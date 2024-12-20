package com.example.ucp2

import android.app.Application

class KrsApp : Application() {
                                            // fungsinya untuk menyimpan instance containerapp
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
                                            // membuat instance containerapp
        containerApp = ContainerApp(this)
                                            // instance adalah yang dibuat dari class
    }
}