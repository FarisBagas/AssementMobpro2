package org.d3if0024.mobproassement.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if0024.mobproassement.model.Pesanan
@Dao
interface PesananDao {
    @Insert
    suspend fun insert(pesanan: Pesanan)

    @Update
    suspend fun update(pesanan: Pesanan)
    @Query("SELECT * FROM pesanan_1 ORDER BY size ASC ")
    fun getPesanan(): Flow<List<Pesanan>>

    @Query("SELECT * FROM pesanan_1 WHERE id = :id")
    suspend fun getPesananById(id: Long):Pesanan?

    @Query("DELETE FROM pesanan_1 WHERE id = :id")
    suspend fun deleteById(id:Long)


}