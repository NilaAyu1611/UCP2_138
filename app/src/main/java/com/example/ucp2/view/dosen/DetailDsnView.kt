package com.example.ucp2.view.dosen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.costumwidget.TopAppBar
import com.example.ucp2.view.viewmodel.Dosen.DetailDsnViewModel
import com.example.ucp2.view.viewmodel.PenyediaViewModel

@Composable
fun DetailDsnView(
    modifier: Modifier = Modifier,
    viewModel: DetailDsnViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onBack: () -> Unit = {}
){
    Scaffold (
        topBar = {
            TopAppBar(
                judul = "Detail Dosen",
                showBackButton = true,
                onBack = onBack
            )
        }
    ){innerPadding ->
        val detailUiState by viewModel.detailUiState.collectAsState()

        BodyDetailDsn(
            modifier = Modifier.padding(innerPadding),
            detailUiState = detailUiState
        )

    }
}