package com.example.ucp2.view.viewmodel

import com.example.ucp2.Data.entity.Matakuliah


//meyimpan input form kedlm entity
fun MatakuliahEvent.toMatakuliahEntity():Matakuliah = Matakuliah(
    kode = kode,
    nama = nama,
    sks = sks,
    semester = semester,
    jenismk = jenismk,
    dosenpengampu = dosenpengampu
)


//data class variabel yang menyimpan data input form
data class MatakuliahEvent(
    val kode:String = "",
    val nama: String = "",
    val sks: String = "",
    val semester: String = "",
    val jenismk: String = "",
    val dosenpengampu: String = "",
)