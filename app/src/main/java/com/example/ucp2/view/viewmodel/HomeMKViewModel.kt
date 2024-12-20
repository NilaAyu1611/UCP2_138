package com.example.ucp2.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.Data.entity.Matakuliah
import com.example.ucp2.repository.RepositoryMK
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeMKViewModel (
    private val repositoryMK: RepositoryMK
): ViewModel(){
    val homeMKUiState: StateFlow<HomeMKUiState> = repositoryMK.getAllMatakuliah()
        .filterNotNull()
        .map {
            HomeMKUiState(
                listMK = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(HomeMKUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeMKUiState (
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeMKUiState(
                isLoading = true,
            )
        )

}


data class HomeMKUiState(
    val listMK: List<Matakuliah> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)