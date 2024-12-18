package com.example.ucp2.repository

import com.example.ucp2.Data.dao.MataKuliahDao
import com.example.ucp2.Data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

class LocalRepositoryMK(
    private val mataKuliahDao: MataKuliahDao
): RepositoryMK {
    override suspend fun insertMatakuliah(matakuliah: Matakuliah) {
        mataKuliahDao.insertMatakuliah(matakuliah)
    }

    override fun getAllMatakuliah(): Flow<List<Matakuliah>> {       // memanggil fungsi ini untuk mendapatkan semua data mk dlm bentuk flow
        return mataKuliahDao.getAllMatakuliah()
    }

    override fun getMatakuliah(kode: String): Flow<Matakuliah> {       //fungsi untuk mengambil data mk berdasarkan kode
        return mataKuliahDao.getMatakuliah(kode)
    }
}