package com.example.ucp2.view.matakuliah

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.Data.entity.Matakuliah
import com.example.ucp2.ui.costumwidget.TopAppBar

import com.example.ucp2.view.viewmodel.Matakuliah.DetailMKViewModel
import com.example.ucp2.view.viewmodel.Matakuliah.DetailMKlUiState
import com.example.ucp2.view.viewmodel.Matakuliah.toMatakuliahEntity
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
                onBack = onBack,
                titleColor = Color.Cyan,
                iconColor = Color.White
            )

        },
        containerColor = Color(0xFF041137),
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

@Composable
fun BodyDetailMK(
    modifier: Modifier = Modifier,
    detailMKUiState: DetailMKlUiState = DetailMKlUiState(),
    onDeleteClick: () -> Unit = {}
) {
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    when {
        detailMKUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        detailMKUiState.isUiEventNotEmpty -> {
            Column (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                ItemDetailMK(
                    matakuliah = detailMKUiState.detailUiEvent.toMatakuliahEntity(),
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button (
                    onClick = {
                        deleteConfirmationRequired = true
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .height(48.dp)
                        .align(Alignment.End),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFec8e8e),             // Warna latar belakang tombol
                        contentColor = Color.Black                          // Warna teks tombol
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete",
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )

                }
                if (deleteConfirmationRequired) {
                    DeleteConfirmationDialog(
                        onDeleteConfirm = {
                            deleteConfirmationRequired = false
                            onDeleteClick()
                        },
                        onDeleteCancel = { deleteConfirmationRequired = false },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
        detailMKUiState.isUiEventEmpty -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Data tidak ditemukan",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun ItemDetailMK(
    modifier: Modifier = Modifier,
    matakuliah: Matakuliah
) {
    Card (
        modifier = modifier.fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFf1ece0), // Warna awal gradien
                        Color(0xFF9adbff)  // Warna akhir gradien
                    )

                ),
                shape = RoundedCornerShape(16.dp),
            ),
        colors = CardDefaults.cardColors(               // Pastikan transparansi teks tetap terlihat
            containerColor = Color.Transparent
        ),


    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailMK(judul = "Kode", isinya = matakuliah.kode)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMK(judul = "Nama", isinya = matakuliah.nama)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMK(judul = "SKS", isinya = matakuliah.sks.toString())
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMK(judul = "Semester", isinya = matakuliah.semester)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMK(judul = "Jenis MK", isinya = matakuliah.jenismk)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMK(judul = "Dosen Pengampu", isinya = matakuliah.dosenpengampu)
        }
    }
}

@Composable
fun ComponentDetailMK(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /* Do nothing */ },
        title = { Text("Delete Data") },
        text = { Text("Apakah anda yakin ingin menghapus data?") },
        modifier = modifier,
        dismissButton = {
            TextButton (onClick = onDeleteCancel) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }
    )
}
