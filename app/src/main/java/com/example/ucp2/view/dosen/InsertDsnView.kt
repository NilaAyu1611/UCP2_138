package com.example.ucp2.view.dosen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.costumwidget.TopAppBar
import com.example.ucp2.ui.navigation.AlamatNavigasi
import com.example.ucp2.view.viewmodel.Dosen.DosenEvent
import com.example.ucp2.view.viewmodel.Dosen.DosenUIState
import com.example.ucp2.view.viewmodel.Dosen.DosenViewModel
import com.example.ucp2.view.viewmodel.Dosen.FormErrorState
import com.example.ucp2.view.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

//@Preview(showBackground = true)
object DestinasiInsert : AlamatNavigasi{
    override val route: String = "insert_dsn"
}

@Composable
fun InsertDsnView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DosenViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiState = viewModel.uiState         //mengambil ui state dari VM
    val snackbarHostState = remember {SnackbarHostState()}      // Snacbar state/menampilkan pesan kpd user terkait ui
    val corutineScope = rememberCoroutineScope()

    //observasi perubahan snackbarmessage
    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            corutineScope.launch {
                snackbarHostState.showSnackbar(message)         // tampilan snacbar
                viewModel.resetSnackBarMessage()
            }

        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Dosen",
                titleColor = Color.Cyan,
                iconColor = Color.White
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)},
        containerColor = Color(0xFF041137)
    ) {padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(16.dp)
        ) {
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = MaterialTheme.shapes.medium,

            ) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFFdbf5f3)) // Dark blue background for the form
                        .padding(16.dp)
                ) {
                    // Isi Body
                    InsertBodyDsn(
                        uiState = uiState,
                        onValueChange = { updatedEvent ->
                            viewModel.updateState(updatedEvent) // update state di viewmodel
                        },
                        onClick = {
                            viewModel.saveData()        //simpan data
                            onNavigate()
                        }
                    )
                }
            }
        }
    }



}

@Composable
fun InsertBodyDsn(
    modifier: Modifier = Modifier,
    onValueChange: (DosenEvent) -> Unit,
    uiState: DosenUIState,
    onClick: () -> Unit
){
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        FormDosen(
            dosenEvent = uiState.dosenEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2dc3cd),             // Warna latar belakang tombol
                contentColor = Color.Black                          // Warna teks tombol
            )
        ) {
            Text("Simpan",
                fontSize = 23.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

@Composable
fun FormDosen(
    dosenEvent: DosenEvent = DosenEvent(),
    onValueChange: (DosenEvent) -> Unit = {},
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
){
    val jeniskelamin = listOf("Laki-laki", "Perempuan")

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dosenEvent.nidn,
            onValueChange = {
                onValueChange(dosenEvent.copy(nidn = it))
            },
            label = { Text("NIDN") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = ""
                )
            },
            isError = errorState.nidn != null,
            placeholder = { Text("Masukkan NIDN") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(
            text = errorState.nidn ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dosenEvent.nama,
            onValueChange = {
                onValueChange(dosenEvent.copy(nama = it))
            },
            label = { Text("Nama") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = ""
                )
            },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan Nama") },
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Jenis Kelamin")
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            jeniskelamin.forEach { jk ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = dosenEvent.jeniskelamin == jk,
                        onClick = {
                            onValueChange(dosenEvent.copy(jeniskelamin = jk))
                        },
                    )
                    Text(
                        text = jk,
                    )
                }
            }

        }





    }
}