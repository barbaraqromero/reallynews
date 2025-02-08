package com.example.reallynews.presentation.ui.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.reallynews.domain.model.NewsModel
import com.example.reallynews.presentation.viewmodel.NewsState
import com.example.reallynews.presentation.viewmodel.NewsViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun NewsScreen(viewModel: NewsViewModel = koinViewModel()) {
    val newsState by viewModel.newsState.observeAsState(NewsState.Loading)

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Jornal Digital", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        when (newsState) {
            is NewsState.Loading -> CircularProgressIndicator()
            is NewsState.Success -> NewsList((newsState as NewsState.Success).data)
            is NewsState.Error -> Text("Erro: ${(newsState as NewsState.Error).message}")
        }
    }
}

@Composable
fun NewsList(newsList: List<NewsModel>) {
    LazyColumn {
        itemsIndexed(newsList) { _, news ->
            NewsItem(news)
        }
    }
}

@Composable
fun NewsItem(news: NewsModel) {
    val context = LocalContext.current
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp).clickable {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(news.url)))
    }) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(painter = rememberImagePainter(news.imageUrl), contentDescription = null, modifier = Modifier.fillMaxWidth().height(200.dp))
            Text(news.title, fontWeight = FontWeight.Bold)
            Text(news.description, maxLines = 3)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewsScreen() {
    NewsScreen()
}