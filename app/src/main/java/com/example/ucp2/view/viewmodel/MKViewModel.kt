package com.example.ucp2.view.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import androidx.lifecycle.ViewModel
import com.example.ucp2.Data.entity.Matakuliah
import com.example.ucp2.repository.RepositoryMK


class MKViewModel (private val repositoryMK: RepositoryMK) : ViewModel(){

    var uiState by mutableStateOf(MKUIState())

    fun update(matakuliahEvent: MatakuliahEvent){
        uiState = uiState.copy(
            matakuliahEvent = matakuliahEvent,
        )

    }

    private fun validateFields(): Boolean{
        val event = uiState.matakuliahEvent
        val errorState = FormErrorStateMK (
            kode = if (event.kode.isNotEmpty()) null else "Kode tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",



        )
    }
    
}



//untuk merubah state/perubahan
data class MKUIState(
    val matakuliahEvent: MatakuliahEvent = MatakuliahEvent(),
    val isEntryValid: FormErrorStateMK = FormErrorStateMK(),
    val snackBarMessage: String? = null,
)

data class FormErrorStateMK(
    val kode: String? = null,
    val nama: String? = null,
    val sks: String? = null,
    val semester: String? = null,
    val jenismk: String? = null,
    val dosenpengampu: String? = null
){
    fun isValid(): Boolean{
        return kode == null && nama == null && sks == null &&
                semester == null && jenismk == null && dosenpengampu == null
    }
}


//meyimpan input form kedlm entity
fun MatakuliahEvent.toMatakuliahEntity():Matakuliah = Matakuliah(
    kode = kode,
    nama = nama,
    sks = sks,
    semester = semester,
    jenismk = jenismk,
    dosenpengampu = dosenpengampu
)


//data class variabel yang menyimpan data input form
data class MatakuliahEvent(
    val kode:String = "",
    val nama: String = "",
    val sks: String = "",
    val semester: String = "",
    val jenismk: String = "",
    val dosenpengampu: String = "",
)