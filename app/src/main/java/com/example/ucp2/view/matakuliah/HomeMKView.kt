package com.example.ucp2.view.matakuliah

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.costumwidget.TopAppBar
import com.example.ucp2.view.viewmodel.Matakuliah.HomeMKUiState
import com.example.ucp2.view.viewmodel.Matakuliah.MKViewModel
import com.example.ucp2.view.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeMKView(
    viewModel: MKViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddMk: () -> Unit = {},
    onDetailClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
){
    Scaffold (
        topBar = {
            TopAppBar(
                judul = "Daftar Mata Kuliah",
                showBackButton = false,
                onBack = {}
            )
        },
        floatingActionButton = {
            FloatingActionButton (
                onClick = onAddMk,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Mata Kuliah",
                )
            }
        }
    ) { innerPadding ->
        val homeMKUiState by homeMKUiState.collecttAsState()


        BodyHomeMKView(
            homeMKUiState = homeMKUiState,
            onclick = {
                onDetailClick(it)
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeMKView(
    homeMKUiState: HomeMKUiState,
    onclick: (String) -> Unit ={},
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    when {
        homeMKUiState.isLoading -> {
            // Menampilkan indikator loading
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        homeMKUiState.isError -> {
            // Menampilkan pesan error
            LaunchedEffect(homeMKUiState.errorMessage) {
                homeMKUiState.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }

        homeMKUiState.listMK.isEmpty() -> {
            // Menampilkan pesan
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada data mata kuliah.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        else -> {
            // Menampilkan daftar mata kuliah
            ListMatakuliah(
                listMk = homeMKUiState.listMK,
                onClik = {
                    onclick(it)
                },
                modifier = modifier
            )
        }
    }
}