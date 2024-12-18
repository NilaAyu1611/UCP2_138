package com.example.ucp2.repository

import com.example.ucp2.Data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

interface RepositoryMK {
    suspend fun insertMatakuliah(matakuliah: Matakuliah)


}