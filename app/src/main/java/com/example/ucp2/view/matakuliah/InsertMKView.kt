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
import com.example.ucp2.view.viewmodel.Matakuliah.MatakuliahEvent


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