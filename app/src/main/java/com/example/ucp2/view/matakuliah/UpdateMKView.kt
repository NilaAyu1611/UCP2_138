package com.example.ucp2.view.matakuliah

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.costumwidget.TopAppBar
import com.example.ucp2.view.viewmodel.Matakuliah.UpdateMKViewModel
import com.example.ucp2.view.viewmodel.PenyediaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun UpdateMKView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateMKViewModel = viewModel(factory = PenyediaViewModel.Factory)   //inisialisasi viewmodel
){

    val uiState = viewModel.updateMKUIState                        // ambil UI state dari ViewModel
    val snackbarHostState = remember  { SnackbarHostState() }        // Snackbar state
    val coroutineScope = rememberCoroutineScope()




    // Observasi perubahan snackbarMessage
    LaunchedEffect (uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Long
                )
                viewModel.resetSnackBarMessage()
            }
        }
    }
    Scaffold (
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },     // Tempatkan Snackbar di Scaffold
        topBar = {
            TopAppBar(
                judul = "Edit Mata Kuliah",
                showBackButton = true,
                onBack = onBack,
                titleColor = Color.Cyan,
                iconColor = Color.White
            )

        }
    ) { padding ->
        Column (
            modifier = Modifier
                .fillMaxHeight()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF2f6682), // Warna cerah biru muda
                            Color(0xFF041137)  // Warna biru yang lebih intens
                        )
                    )
                )
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = MaterialTheme .shapes.medium,
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFFdbf5f3)) // Background for the form
                        .padding(16.dp)
                ){
                    InsertBodyMk (
                        uiState = uiState,
                        onValueChange = { updatedEvent ->
                            viewModel.updateState(updatedEvent)         // Update state di ViewModel
                        },
                        onClick = {
                            coroutineScope.launch {
                                if (viewModel.validateFields()) {
                                    viewModel.updateData()
                                    delay(600)
                                    withContext(Dispatchers.Main) {
                                        onNavigate()                    // Navigasi di main thread
                                    }
                                }
                            }
                        }

                    )

                }
            }


        }
    }
}


