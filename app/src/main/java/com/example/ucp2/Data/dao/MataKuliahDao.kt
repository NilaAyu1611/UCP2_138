package com.example.ucp2.Data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.ucp2.Data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

@Dao
interface MataKuliahDao {
    @Query("select* from matakuliah")
    fun getAllMatakuliah(): Flow<List<Matakuliah>>


}