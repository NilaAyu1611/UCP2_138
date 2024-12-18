package com.example.ucp2.Data.database

import androidx.room.Database
import com.example.ucp2.Data.dao.DosenDao
import com.example.ucp2.Data.dao.MataKuliahDao
import com.example.ucp2.Data.entity.Dosen
import com.example.ucp2.Data.entity.Matakuliah

//Mendifinisikan databse dengan tabel dosen & matakuliah
@Database(entities = [Dosen::class, Matakuliah::class],       //Daftar tabel dalam DB
    version = 1,
    exportSchema = false)
abstract class krsDatabase(){

    abstract fun dosenDao(): DosenDao       // Dao untuk mengakses data di tabel dosen

    abstract fun mataKuliahDao(): MataKuliahDao     // Dao untuk mengakses data di tabel matakuliah


}
