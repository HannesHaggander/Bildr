package com.nattfall.bildr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nattfall.bildr.ui.Gallery
import com.nattfall.bildr.ui.theme.BildrTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BildrTheme {
                Gallery()
            }
        }
    }
}
