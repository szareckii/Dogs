package com.zareckii.dogs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.zareckii.dogs.ui.BottomNavScreen
import com.zareckii.dogs.ui.theme.DogsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DogsTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    BottomNavScreen()
                }
            }
        }
    }
}
