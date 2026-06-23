package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.ui.ProjModApp
import com.example.ui.ProjectViewModel
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    private val viewModel: ProjectViewModel by viewModels {
        ProjectViewModel.provideFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDarkThemeState by viewModel.isDarkTheme.collectAsState()
            val darkTheme = isDarkThemeState ?: androidx.compose.foundation.isSystemInDarkTheme()
            MyApplicationTheme(darkTheme = darkTheme) {
                ProjModApp(viewModel = viewModel)
            }
        }
    }
}
