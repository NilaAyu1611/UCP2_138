package com.example.ucp2.view.viewmodel.Matakuliah

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.repository.RepositoryMK
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
    private val _kode: String = checkNotNull(savedStateHandle[DestinasiDetail.KODE])

    val detailMKUiState: StateFlow<DetailMKUiState> = repositoryMK.getMatakuliah(_kode)
        .filterNotNull()
        .map{
            DetailMKUiState(
                detailUiEvent = it.toDetaiMKlUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailMKUiState(isLoading = true))
            delay(600)
        }
        .catch{
            emit(
                DetailMKUiState(
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
        detailMKUiState.value.detailUiEvent.toMahasiswaEntity().let {
            viewModelScope.launch {
                repositoryMK.deleteMatakuliah(it)
            }
        }
    }
}

