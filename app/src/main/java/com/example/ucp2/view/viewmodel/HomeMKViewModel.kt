package com.example.ucp2.view.viewmodel

import com.example.ucp2.Data.entity.Matakuliah




data class HomeMKUiState(
    val listMK: List<Matakuliah> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)