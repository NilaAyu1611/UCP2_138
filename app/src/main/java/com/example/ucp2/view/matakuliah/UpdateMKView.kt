package com.example.ucp2.view.matakuliah

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.view.viewmodel.Matakuliah.UpdateMKViewModel
import com.example.ucp2.view.viewmodel.PenyediaViewModel

@Composable
fun UpdateMKView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateMKViewModel = viewModel(factory = PenyediaViewModel.Factory)   //inisialisasi viewmodel
){

    val uiState = viewModel.updateMKUIState                           // ambil UI state dari ViewModel
    val snackbarHostState = remember  { SnackbarHostState() }        // Snackbar state
    val coroutineScope = rememberCoroutineScope()


}