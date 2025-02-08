package com.example.reallynews.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.reallynews.presentation.ui.screen.NewsScreen
import com.example.reallynews.presentation.viewmodel.NewsViewModel
import com.example.reallynews.ui.theme.ReallyNewsTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsActivity : ComponentActivity() {
    private val newsViewModel: NewsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsScreen(newsViewModel)
        }
    }
}