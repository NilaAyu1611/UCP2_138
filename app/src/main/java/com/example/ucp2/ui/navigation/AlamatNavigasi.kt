package com.example.ucp2.ui.navigation

interface AlamatNavigasi{
    val route: String
}

//Navigasi untuk Home Utama
object DestinasiHome : AlamatNavigasi{
    override val route = "home"
}

