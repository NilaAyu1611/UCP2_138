package com.example.ucp2.view

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ucp2.R
import com.example.ucp2.ui.navigation.DestinasiHomeDosen
import com.example.ucp2.ui.navigation.DestinasiHomeMK


@Composable
fun HomeView(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    Column (
        modifier = modifier
            .fillMaxSize()
            .background(                               // Memberi warna background
            brush = Brush.linearGradient(           // Warna gradient
                colors = listOf(
                    Color(0xFFffffff),
                    Color(0xFF12117e),
                    Color(0xFF010111)
                )
            )
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ti),
            contentDescription = "",
            modifier = Modifier.size(200.dp).clip(CircleShape),
            contentScale = ContentScale.Crop

        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Welcome to 🏠",
            style = TextStyle(
                color = Color(0xFF83def5), // Warna teks (contoh oranye)
                fontSize = 39.sp, // Ukuran font
                fontWeight = FontWeight.Bold // Ketebalan font
            ),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))


        // Dosen Section
        Button (
            onClick = {
                navController.navigate(DestinasiHomeDosen.route)
            },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFc1c2e7),             // Warna latar belakang tombol
                contentColor = Color.Black                          // Warna teks tombol
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Dosen Icon",
                tint = Color.Blue,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = "Dosen",
                fontSize = 23.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Monospace
            )

        }

        Spacer(modifier = Modifier.height(16.dp))


        // Mata Kuliah Section
        Button(
            onClick = {
                navController.navigate(DestinasiHomeMK.route)
            },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFc1c2e7),             // Warna latar belakang tombol
                contentColor = Color.Black                          // Warna teks tombol
            )
        ) {
            Icon(
                imageVector = Icons.Filled.List,
                contentDescription = "MK Icon",
                tint = Color.Blue,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = "Mata Kuliah",
                fontSize = 23.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

