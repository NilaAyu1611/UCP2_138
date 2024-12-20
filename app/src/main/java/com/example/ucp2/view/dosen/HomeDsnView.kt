package com.example.ucp2.view.dosen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.Data.entity.Dosen
import com.example.ucp2.view.viewmodel.Dosen.HomeDsnViewModel
import com.example.ucp2.view.viewmodel.Dosen.HomeUiState
import com.example.ucp2.view.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeDsnView(
    viewModel: HomeDsnViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddDsn: () -> Unit = {},
    onDetailClik: (String) -> Unit = {},
    modifier: Modifier = Modifier
){
    Scaffold (
        topBar = {
            TopAppBar(
                judul = "Daftar Dosen",
                showBackButton = false,
                onBack = {}
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddDsn,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Dosen",
                )
            }
        }

    ){innerPadding ->
        val homeUiState by viewModel.homeUIState.collectAsState()

        BodyHomeDsnView(
            homeUiState = homeUiState,
            onclick = {
                onDetailClik
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeDsnView (
    homeUiState: HomeUiState,
    onclick: (String) -> Unit = { },
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }      // Snackbar state
    when {
        homeUiState.isLoading -> {
            // Menampilkan indikator loading
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        homeUiState.isError -> {
            // Menampilkan pesan error
            LaunchedEffect(homeUiState.errorMessage) {
                homeUiState.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)           // Tampilkan Snackbar
                    }
                }
            }
        }

        homeUiState.listDsn.isEmpty() -> {
            // Menampilkan pesan
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada data dosen.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        else -> {
            // Menampilkan daftar dosen
            ListDosen(
                listDsn = homeUiState.listDsn,
                onClik = {
                    onclick(it)
                    println(it)
                },
                modifier = modifier
            )
        }
    }

}

@Composable
fun ListDosen(
    listDsn: List<Dosen>,
    modifier: Modifier = Modifier,
    onClik: (String) -> Unit = { }
){
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = listDsn,
            itemContent = {Dsn ->
                CardDsn(
                    Dsn = Dsn,
                    onClik = {onClik(Dsn.nama)}
                )
            }
        )
    }
}
