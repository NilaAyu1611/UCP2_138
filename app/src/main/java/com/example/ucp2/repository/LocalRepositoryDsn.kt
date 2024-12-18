package com.example.ucp2.repository

import com.example.ucp2.Data.dao.DosenDao
import com.example.ucp2.Data.entity.Dosen
import kotlinx.coroutines.flow.Flow

//menghubungkan DosenDao dengan operasi yang didefinisikan dlaam RepositoryDsn
class LocalRepositoryDsn (
    private val dosenDao: DosenDao
): RepositoryDsn{
    override suspend fun insertDosen(dosen: Dosen) {
        dosenDao.insertDosen(dosen)
    }

    override fun getAllDosen(): Flow<List<Dosen>> {
        return dosenDao.getAllDosen()
    }

    override fun getDosen(nama: String): Flow<Dosen> {
        return dosenDao.getDosen(nama)
    }

}