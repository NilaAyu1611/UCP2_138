package com.example.ucp2.Data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2.Data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

@Dao
interface MataKuliahDao {
    @Query("select* from matakuliah")
    fun getAllMatakuliah(): Flow<List<Matakuliah>>

    @Insert
    suspend fun insertMatakuliah(
        matakuliah: Matakuliah
    )

    @Query("select* from matakuliah where kode= :kode")
    fun getMatakuliah(kode: String): Flow<Matakuliah>

    @Delete
    suspend fun deleteMatakuliah(matakuliah: Matakuliah)
}