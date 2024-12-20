package com.example.ucp2.view.viewmodel.Matakuliah

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.Data.entity.Matakuliah
import com.example.ucp2.repository.RepositoryMK
import com.example.ucp2.ui.navigation.DestinasiDetailMK
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailMKViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryMK: RepositoryMK,
): ViewModel(){
    private val _kode: String = checkNotNull(savedStateHandle[DestinasiDetailMK.KODE])

    val detailMKUiState: StateFlow<DetailMKlUiState> = repositoryMK.getMatakuliah(_kode)
        .filterNotNull()
        .map{
            DetailMKlUiState(
                detailUiEvent = it.toDetailMKUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailMKlUiState(isLoading = true))
            delay(600)
        }
        .catch{
            emit(
                DetailMKlUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailMKlUiState(
                isLoading = true,
            ),
        )

    fun deleteMatakuliah(){
        detailMKUiState.value.detailUiEvent.toMatakuliahEntity().let {
            viewModelScope.launch {
                repositoryMK.deleteMatakuliah(it)
            }
        }
    }
}


class DetailMKlUiState(
    val detailUiEvent: MatakuliahEvent = MatakuliahEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == MatakuliahEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != MatakuliahEvent()
}

// memindahkan data class dari entity ke ui
fun Matakuliah.toDetailMKUiEvent(): MatakuliahEvent{
    return MatakuliahEvent(
        kode = kode,
        nama = nama,
        sks = sks,
        semester = semester,
        jenismk = jenismk,
        dosenpengampu = dosenpengampu
    )
}