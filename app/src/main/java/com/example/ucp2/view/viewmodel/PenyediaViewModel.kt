package com.example.ucp2.view.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.KrsApp

import com.example.ucp2.view.viewmodel.Dosen.DosenViewModel
import com.example.ucp2.view.viewmodel.Dosen.HomeDsnViewModel
import com.example.ucp2.view.viewmodel.Matakuliah.DetailMKViewModel
import com.example.ucp2.view.viewmodel.Matakuliah.HomeMKViewModel
import com.example.ucp2.view.viewmodel.Matakuliah.MKViewModel
import com.example.ucp2.view.viewmodel.Matakuliah.UpdateMKViewModel

object PenyediaViewModel {

    val Factory = viewModelFactory {
        initializer {
            DosenViewModel(
                krsApp().containerApp.repositoryDsn
            )
        }

        initializer {
            HomeDsnViewModel(
                krsApp().containerApp.repositoryDsn
            )
        }



        // Matakuliah Viewmodels
        initializer {
            MKViewModel(
                krsApp().containerApp.repositoryMK
            )
        }

        initializer {
            HomeMKViewModel(
                krsApp().containerApp.repositoryMK
            )
        }

        initializer {
            DetailMKViewModel(
                createSavedStateHandle(),
                krsApp().containerApp.repositoryMK
            )
        }
        initializer {
            UpdateMKViewModel(
                createSavedStateHandle(),
                krsApp().containerApp.repositoryMK
            )
        }


    }
}


fun CreationExtras.krsApp(): KrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KrsApp)