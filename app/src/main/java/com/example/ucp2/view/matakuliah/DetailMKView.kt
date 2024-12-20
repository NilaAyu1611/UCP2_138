package com.example.ucp2.view.matakuliah

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.costumwidget.TopAppBar
import com.example.ucp2.view.viewmodel.Matakuliah.DetailMKViewModel
import com.example.ucp2.view.viewmodel.PenyediaViewModel

@Composable
fun DetailMKView(
    modifier: Modifier = Modifier,
    viewModel: DetailMKViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onBack: () -> Unit = {},
    onEditClick: (String) -> Unit = {},
    onDeleteClick: () -> Unit = {}
){
    Scaffold (
        topBar = {
            TopAppBar(
                judul = "Detail Mata Kuliah",
                showBackButton = true,
                onBack = onBack
            )
        },
        floatingActionButton = {
            FloatingActionButton (
                onClick = {
                    onEditClick(viewModel.detailMKUiState.value.detailUiEvent.kode)
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Mata Kuliah",
                )
            }
        }
    ) { innerPadding ->
        val detailMKUiState by viewModel.detailMKUiState.collectAsState()

        BodyDetailMK(
            modifier = Modifier.padding(innerPadding),
            detailMKUiState = detailMKUiState,
            onDeleteClick = {
                viewModel.deleteMatakuliah()
                onDeleteClick()
            }
        )
    }
}