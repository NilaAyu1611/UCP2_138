package com.example.ucp2.view.viewmodel.Matakuliah

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.Data.entity.Matakuliah

import com.example.ucp2.repository.RepositoryMK
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMKViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryMK: RepositoryMK
): ViewModel(){
    var updateMKUIState by mutableStateOf(MKUIState())
        private set

    private val _kode: String = checkNotNull(savedStateHandle[DestinasiUpdate.KODE])

    init {
        viewModelScope.launch {
            updateMKUIState = repositoryMK.getMatakuliah(_kode)
                .filterNotNull()
                .first()
                .toUIStateMK()
        }
    }

    fun updateState(matakuliahEvent: MatakuliahEvent){
        updateMKUIState = updateMKUIState.copy(
            matakuliahEvent = matakuliahEvent,
        )
    }

    fun validateFields(): Boolean {
        val event = updateMKUIState.matakuliahEvent
        val errorState = FormErrorStateMK(
            kode = if (event.kode.isNotEmpty()) null else "Kode tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "SKS tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester tidak boleh kosong",
            jenismk = if (event.jenismk.isNotEmpty()) null else "Jenis MK tidak boleh kosong",
            dosenpengampu = if (event.dosenpengampu.isNotEmpty()) null else "Dosen Pengampu tidak boleh kosong"
        )

        updateMKUIState = updateMKUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateData() {
        val currentEvent = updateMKUIState.matakuliahEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMK.updateMatakuliah(currentEvent.toMatakuliahEntity())
                    updateMKUIState = updateMKUIState.copy(
                        snackBarMessage = "Data berhasil diupdate",
                        matakuliahEvent = MatakuliahEvent(),
                        isEntryValid = FormErrorStateMK()
                    )
                    println(
                        "snackBarMessage diatur: ${
                            updateMKUIState.snackBarMessage
                        }"
                    )
                } catch (e: Exception) {
                    updateMKUIState = updateMKUIState.copy(
                        snackBarMessage = "Data gagal diupdate"
                    )
                }
            }
        } else {
            updateMKUIState = updateMKUIState.copy(
                snackBarMessage = "Input tidak valid, periksa kembali data Anda"
            )
        }
    }

    fun resetSnackBarMessage() {
        updateMKUIState = updateMKUIState.copy(snackBarMessage = null)
    }

}
fun Matakuliah.toUIStateMK(): MKUIState = MKUIState(
    matakuliahEvent = this.toDetailMKUiEvent()
)
