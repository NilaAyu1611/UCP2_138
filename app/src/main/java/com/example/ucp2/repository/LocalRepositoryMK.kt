package com.example.ucp2.repository

import com.example.ucp2.Data.dao.MataKuliahDao
import com.example.ucp2.Data.entity.Matakuliah

class LocalRepositoryMK(
    private val mataKuliahDao: MataKuliahDao
): RepositoryMK {
    override suspend fun insertMatakuliah(matakuliah: Matakuliah) {
        mataKuliahDao.insertMatakuliah(matakuliah)
    }
}