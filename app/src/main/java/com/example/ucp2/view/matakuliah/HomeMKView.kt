package com.example.ucp2.view.matakuliah

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.costumwidget.TopAppBar
import com.example.ucp2.view.viewmodel.Matakuliah.MKViewModel
import com.example.ucp2.view.viewmodel.PenyediaViewModel

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