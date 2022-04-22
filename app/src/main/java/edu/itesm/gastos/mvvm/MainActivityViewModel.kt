package edu.itesm.gastos.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import edu.itesm.gastos.dao.GastoDao
import edu.itesm.gastos.database.GastosDB
import edu.itesm.gastos.entities.Gasto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random


class MainActivityViewModel : ViewModel(){
    var liveData: MutableLiveData<List<Gasto>>
    var liveTotal: MutableLiveData<Double>

    init {
        liveData = MutableLiveData()
        liveTotal = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<Gasto>>{
        return liveData
    }

    fun getLiveTotalObserver(): MutableLiveData<Double> {
        return liveTotal
    }

    fun getGastos(gastoDao: GastoDao){
        CoroutineScope(Dispatchers.IO).launch {
            liveData.postValue(gastoDao.getAllGastos())
        }
    }

    fun getTotalGastos(gastoDao: GastoDao) {
        CoroutineScope(Dispatchers.IO).launch {
            val totalGastos = gastoDao.getTotalMontoGastos()
            liveTotal.postValue(totalGastos)
        }
    }

    fun addGasto(gastoDao: GastoDao, gasto: Gasto) {
        CoroutineScope(Dispatchers.IO).launch {
            gastoDao.insertGasto(gasto)
            liveData.postValue(gastoDao.getAllGastos())
        }
    }
}