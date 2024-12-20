package com.example.ucp2.ui.navigation

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

//Navigasi untuk detail dosen
object DestinasiDetailDosen : AlamatNavigasi{
    override val route = "detailDosen"
    const val nama = "nama"
    val routeWithArg = "$route/{$nama}"
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
