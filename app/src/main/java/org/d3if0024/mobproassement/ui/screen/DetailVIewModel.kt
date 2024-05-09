package org.d3if0024.mobproassement.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0024.mobproassement.database.PesananDao
import org.d3if0024.mobproassement.model.Pesanan


class DetailViewModel(private val dao: PesananDao) : ViewModel() {

    fun insert(size: String, topping: String, drink: String) {
        val pesanan = Pesanan(
            size = size,
            topping = topping,
            drink = drink
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(pesanan)
        }
    }

    suspend fun getPesanan(id: Long): Pesanan? {
        return dao.getPesananById(id)
    }

    fun update(id: Long, size: String, topping: String, drink: String) {
        val pesanan = Pesanan(
            id, size, topping, drink
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(pesanan)
        }

    }

    fun delete(id: Long){
        viewModelScope.launch(Dispatchers.IO){
            dao.deleteById(id)
        }
    }

}