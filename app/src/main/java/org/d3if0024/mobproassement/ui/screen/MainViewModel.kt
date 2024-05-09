package org.d3if0024.mobproassement.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if0024.mobproassement.model.Pesanan

    class MainViewModel : ViewModel() {

        val data = getDataDummy()
        private  fun getDataDummy(): List<Pesanan>{
            val data = mutableListOf<Pesanan>()
            for (i in 29 downTo 20){
                data.add(
                    Pesanan(
                        i.toLong(),
                        "Reguler",
                        "Daging",
                        "Cola"
                    )
                )
            }
            return data
        }
    }
