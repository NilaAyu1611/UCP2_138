package com.example.ucp2.view.matakuliah

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.Data.entity.Matakuliah
import com.example.ucp2.ui.costumwidget.TopAppBar
import com.example.ucp2.view.viewmodel.Matakuliah.HomeMKUiState
import com.example.ucp2.view.viewmodel.Matakuliah.HomeMKViewModel
import com.example.ucp2.view.viewmodel.Matakuliah.MKViewModel
import com.example.ucp2.view.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeMKView(
    MKViewModel: HomeMKViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddMk: () -> Unit = {},
    onDetailClick: (String) -> Unit = {},
    onBack: () -> Unit,
    modifier: Modifier = Modifier
){
    Scaffold (
        topBar = {
            TopAppBar(
                judul = "Daftar Mata Kuliah",
                showBackButton = true,
                onBack = onBack,
                titleColor = Color.Cyan,
                iconColor = Color.White

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
        },
        containerColor = Color(0xFF041137)
    ) { innerPadding ->
        val homeMKUiState by MKViewModel.homeMKUiState.collectAsState()


        BodyHomeMKView(
            homeMKUiState = homeMKUiState,
            onclick = {
                onDetailClick(it)
            },
            modifier = Modifier.padding(innerPadding).padding(top = 16.dp)
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
            ListMataKuliah(
                listMK = homeMKUiState.listMK,
                onClik = {
                    onclick(it)
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListMataKuliah(
    listMK: List<Matakuliah>,
    modifier: Modifier = Modifier,
    onClik: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = listMK,
            itemContent = { mk ->
                CardMK(
                    mk = mk,
                    onClik = { onClik(mk.kode) }
                )
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardMK(
    mk: Matakuliah,
    modifier: Modifier = Modifier,
    onClik: () -> Unit = {}
) {
    Card (
        onClick = onClik,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFdfdfc7), // Gradient starting color
                            Color(0xFF3cebdb)  // Gradient ending color
                        )
                    )
                )
                .fillMaxWidth()
        ){
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Filled.Info, contentDescription = "")
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = mk.kode,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Filled.Create, contentDescription = "")
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = mk.nama,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = "")
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = mk.sks,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Filled.Home, contentDescription = "")
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = mk.semester,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = "")
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = mk.jenismk,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Filled.Person, contentDescription = "")
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = mk.dosenpengampu,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
        }

        }
    }
}
