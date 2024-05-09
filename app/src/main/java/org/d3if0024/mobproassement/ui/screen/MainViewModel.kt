package org.d3if0024.mobproassement.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if0024.mobproassement.database.PesananDao
import org.d3if0024.mobproassement.model.Pesanan

    class MainViewModel (dao: PesananDao) : ViewModel() {

        val data : StateFlow<List<Pesanan>> = dao.getPesanan().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    }
