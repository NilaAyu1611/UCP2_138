package com.example.ucp2.view.matakuliah

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ucp2.view.viewmodel.Dosen.FormErrorState
import com.example.ucp2.view.viewmodel.Matakuliah.FormErrorStateMK
import com.example.ucp2.view.viewmodel.Matakuliah.MKUIState
import com.example.ucp2.view.viewmodel.Matakuliah.MatakuliahEvent

@Composable
fun InsertBodyMk(
    modifier: Modifier = Modifier,
    onValueChange: (MatakuliahEvent) -> Unit,
    uiState: MKUIState,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormMatakuliah(
            matakuliahEvent = uiState.matakuliahEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            dosenList = uiState.dosenList,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Simpan")
        }
    }
}


@Composable
fun FormMatakuliah(
    matakuliahEvent: MatakuliahEvent = MatakuliahEvent(),
    onValueChange: (MatakuliahEvent) -> Unit = {},
    errorState: FormErrorStateMK = FormErrorStateMK(),
    dosenList: List<String>,
    modifier: Modifier = Modifier
){
    val jenisMkOptions = listOf("Matkul Wajib", "Matkul Peminatan")
    var expanded by remember { mutableStateOf(false) }
    var selectedDosen by rememberSaveable{ mutableStateOf("") }

    Column (
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.kode,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(kode = it))
            },
            label = { Text("Kode Mata Kuliah") },
            isError = errorState.kode != null,
            placeholder = { Text("Masukkan Kode Mata Kuliah") },
        )
        Text(
            text = errorState.kode ?: "",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.nama,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(nama = it))
            },
            label = { Text("Nama Mata Kuliah") },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan Nama Mata Kuliah") },
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.sks,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(sks = it))
            },
            label = { Text("SKS Mata Kuliah") },
            isError = errorState.sks != null,
            placeholder = { Text("Masukkan Jumlah SKS Mata Kuliah") },
        )
        Text(
            text = errorState.sks ?: "",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.semester,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(semester = it))
            },
            label = { Text("Semester Mata Kuliah") },
            isError = errorState.semester != null,
            placeholder = { Text("Masukkan Semester Mata Kuliah") },
        )
        Text(
            text = errorState.semester ?: "",
            color = Color.Red
        )


        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Jenis Mata Kuliah")
        Row (
            modifier = Modifier.fillMaxWidth()
        ) {
            jenisMkOptions.forEach { jenis ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = matakuliahEvent.jenismk == jenis,
                        onClick = {
                            onValueChange(matakuliahEvent.copy(jenismk = jenis))
                        },
                    )
                    Text(text = jenis)
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Dosen Pengampu")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = selectedDosen,
            onValueChange = { /* Tidak ada tindakan langsung */ },
            label = { Text("Pilih Dosen Pengampu") },
            readOnly = true,
            trailingIcon = {
                Button (onClick = { expanded = !expanded }) {
                    Text("Pilih")
                }
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            dosenList.forEach { dosen ->
                DropdownMenuItem(
                    onClick = {
                        selectedDosen = dosen
                        onValueChange(matakuliahEvent.copy(dosenpengampu = dosen))
                        expanded = false
                    },
                    text = { Text(dosen) }
                )
            }
        }
        Text(
            text = errorState.dosenpengampu ?: "",
            color = Color.Red
        )
    }

}