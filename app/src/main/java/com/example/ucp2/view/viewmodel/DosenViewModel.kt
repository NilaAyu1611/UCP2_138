package com.example.ucp2.view.viewmodel


import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.Data.entity.Dosen
import com.example.ucp2.repository.RepositoryDsn
import kotlinx.coroutines.launch

class DosenViewModel (private val repositoryDsn: RepositoryDsn): ViewModel(){

    var uiState by mutableStateOf(DosenUIState())

    fun  updateState(dosenEvent: DosenEvent){
        uiState = uiState.copy(
            dosenEvent = dosenEvent,
        )
    }

    private fun validateFields(): Boolean{
        val event = uiState.dosenEvent
        val errorState = FormErrorSate(
            nidn = if (event.nidn.isNotEmpety()) null else "NIDN tidak boleh kosong",
            nama = if (event.nama.isNotEmpety()) null else "Nama tidak boleh kosong",
            jeniskelamin = if (event.jeniskelamin.isNotEmpety()) null else "Jenis Kelamin tidak boleh kosong"
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData(){
        val currentEvent = uiState.dosenEvent
        if (validateFields()){
            viewModelScope.launch {
                try {
                    repositoryDsn.insertDosen((currentEvent.toDoseenEntity()))
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        dosenEvent = DosenEvent(),
                        isEntryValid = FormErrorState()
                    )
                }
                catch (e: Exception){
                    uiState = uiState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }

        }else{
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid, periksa kembali data Anda"
            )
        }
    }

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
