package com.example.ucp2.ui.navigation

import com.example.ucp2.ui.navigation.DestinasiUpdateMK.KODE

interface AlamatNavigasi{
    val route: String
}

//Navigasi untuk Home Utama
object DestinasiHome : AlamatNavigasi{
    override val route = "home"
}

// Navigasi untuk Home Dosen
object DestinasiHomeDosen : AlamatNavigasi {
    override val route = "homeDosen"
}



// Navigasi untuk Home Mata Kuliah
object DestinasiHomeMK : AlamatNavigasi {
    override val route = "homeMK"
}

// Navigasi untuk Detail Matakuliah
object DestinasiDetailMK : AlamatNavigasi {
    override val route = "detailMK"
    const val KODE = "kode" // KODE adalah kunci unik untuk Matakuliah
    val routesWithArg = "$route/{$KODE}"
}

// Navigasi untuk Update Matakuliah
object DestinasiUpdateMK : AlamatNavigasi {
    override val route = "updateMK"
    const val KODE = "kode"
    val routesWithArg = "$route/{$KODE}"
}

object DestinasiInsertMK: AlamatNavigasi {
    override val route: String = "insertMK"
    const val KODE = "kode"
    val routesWithArg = "${route}/{$KODE}"

}


