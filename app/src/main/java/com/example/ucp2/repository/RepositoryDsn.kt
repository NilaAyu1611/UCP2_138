package com.example.ucp2.repository

import com.example.ucp2.Data.entity.Dosen
import kotlinx.coroutines.flow.Flow

interface RepositoryDsn {
    suspend fun insertDosen(dosen: Dosen)
    fun getAllDosen(): Flow<List<Dosen>>        //mendapatkan semua data dosen dlm bentuk aliran flow

}