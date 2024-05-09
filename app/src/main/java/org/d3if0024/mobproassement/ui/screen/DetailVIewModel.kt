package org.d3if0024.mobproassement.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if0024.mobproassement.model.Pesanan


class DetailViewModel : ViewModel() {

    fun getPesanan(id:Long): Pesanan{
        return Pesanan(
            id,
            "Reguler",
            "Daging",
            "Cola",
        )

    }

}