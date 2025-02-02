package com.example.ucp2.dependenciesinjection

import android.content.Context
import com.example.ucp2.Data.database.krsDatabase
import com.example.ucp2.repository.LocalRepositoryDsn
import com.example.ucp2.repository.LocalRepositoryMK
import com.example.ucp2.repository.RepositoryDsn
import com.example.ucp2.repository.RepositoryMK

interface InterfaceContainerApp{
    val repositoryDsn: RepositoryDsn
    val repositoryMK: RepositoryMK
}

class ContainerApp (private val context: Context): InterfaceContainerApp{
    override val repositoryDsn: RepositoryDsn by lazy {
        LocalRepositoryDsn(krsDatabase.getDatabase(context).dosenDao())
    }

    override val repositoryMK: RepositoryMK by lazy {
        LocalRepositoryMK(
            krsDatabase.getDatabase(context).mataKuliahDao(),
            krsDatabase.getDatabase(context).dosenDao()
        )
    }


}