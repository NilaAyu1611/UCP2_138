package com.example.ucp2.repository

import com.example.ucp2.Data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

interface RepositoryMK {
    suspend fun insertMatakuliah(matakuliah: Matakuliah)
    fun getAllMatakuliah(): Flow<List<Matakuliah>>          //mendapatkan semua data dosen dlm bentuk aliran flow
    fun getMatakuliah(kode: String): Flow<Matakuliah>       //mengambil data dosen berdasarkan nama
    suspend fun deleteMatakuliah(matakuliah: Matakuliah)    // menghapus data mk
    suspend fun updateMatakuliah(matakuliah: Matakuliah)    // memperbarui data mk di db

    suspend fun getDosenList(): Flow<List<String>>


}