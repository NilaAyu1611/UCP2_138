package com.example.ucp2.view.viewmodel.Dosen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.Data.entity.Dosen
import com.example.ucp2.repository.RepositoryDsn
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class DetailDsnViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryDsn: RepositoryDsn,
): ViewModel(){
    private val _nama: String = checkNotNull(savedStateHandle[DestinasiDetail.NAMA])

    val detailUiState: StateFlow<DetailUiState> = repositoryDsn.getDosen(_nama)
        .filterNotNull()
        .map{
            DetailUiState(
                detailUiEvent = it.toDetailUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailUiState(isLoading = true))
            delay(600)
        }
        .catch{
            emit(
                DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiState(
                isLoading = true,
            ),
        )
}

class DetailUiState(
    val detailUiEvent: DosenEvent = DosenEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == DosenEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != DosenEvent()
}

// memindahkan data class dari entity ke ui
fun Dosen.toDetailUiEvent(): DosenEvent {
    return DosenEvent(
        nidn = nidn,
        nama = nama,
        jeniskelamin = jeniskelamin
    )
}