package com.example.emiyo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.emiyo.core.design.WardrobeTheme
import com.example.emiyo.navigation.WardrobeApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WardrobeTheme {
                WardrobeApp()
            }
        }
    }
}