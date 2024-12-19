package com.example.ucp2.view.screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.navigation.AlamatNavigasi
import com.example.ucp2.view.viewmodel.DosenViewModel

@Preview(showBackground = true)
object DestinasiInsert : AlamatNavigasi{
    override val route: String = "insert_dsn"
}

@Composable
fun InsertDsnView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DosenViewModel = viewModel(factory = PenyedeiaViewModel.Factory)
){
    val uiState = viewModel.uiState         //mengambil ui state dari VM
    val snackbarHostState = remember {SnackbarHostState()}      // Snacbar state/menampilkan pesan kpd user terkait ui
    val corutineScope = rememberCoroutineScope()



}