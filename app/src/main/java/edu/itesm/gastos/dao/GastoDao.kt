package edu.itesm.gastos.dao

import androidx.room.*
import edu.itesm.gastos.entities.Gasto

@Dao
interface GastoDao {
    @Query("SELECT * FROM Gasto")
    suspend fun getAllGastos(): List<Gasto>

    @Query("SELECT SUM(monto) FROM Gasto")
    suspend fun getTotalMontoGastos(): Double

    @Insert
    suspend fun insertGasto(gasto: Gasto)
}

