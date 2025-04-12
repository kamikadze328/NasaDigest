package com.kamikadze328.nasadigest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kamikadze328.nasadigest.ui.theme.NasaDigestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NasaDigestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            NasaDigestTheme {
                NasaDigestScreenUi()
            }
        }
    }
}