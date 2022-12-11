package id.derysudrajat.netflix.ui.page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.derysudrajat.netflix.MainTopBar
import id.derysudrajat.netflix.data.MovieDatasource
import id.derysudrajat.netflix.model.Movie

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridMovie(list: List<Movie>, onItemClick: (Movie) -> Unit) {
    Scaffold(
        topBar = { MainTopBar() }
    ) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            cells = GridCells.Fixed(2)
        ) {
            items(list) {
                ItemMovie(movie = it, true){ movie ->
                    onItemClick(movie)
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewGridMovie() {
    GridMovie(list = MovieDatasource.getNowPlayingMovie()){}
}