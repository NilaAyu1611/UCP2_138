package com.example.ucp2.view.viewmodel


import androidx.core.app.NotificationCompat.MessagingStyle.Message
import androidx.lifecycle.ViewModel
import com.example.ucp2.Data.entity.Dosen
import com.example.ucp2.repository.RepositoryDsn

class DosenViewModel (private val repositoryDsn: RepositoryDsn): ViewModel(){



}

// untuk merubah state
data class DosenUIState(
    val dosenEvent: DosenEvent = DosenEvent(),
    val isEntryValid: FormErrorState = FormErrorSate(),
    val snackBarMessage: String? = null,
)


//menyimpan input form kedalam entity
fun DosenEvent.toDosenEntity():Dosen = Dosen(
    nidn = nidn,
    nama = nama,
    jeniskelamin = jeniskelamin
)

// data class variabel yang menyimpan data input form
data class DosenEvent(
    val nidn: String = "",
    val nama: String = "",
    val jeniskelamin: String = "",
)
