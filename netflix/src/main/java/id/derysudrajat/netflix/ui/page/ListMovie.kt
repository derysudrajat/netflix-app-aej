package id.derysudrajat.netflix.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.derysudrajat.netflix.MainTopBar
import id.derysudrajat.netflix.data.MovieDatasource
import id.derysudrajat.netflix.model.Movie

@Composable
fun ListMovie(list: List<Movie>, onItemClick: (Movie) -> Unit) {
    Scaffold(
        topBar = { MainTopBar() }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(list) {
                ItemMovie(movie = it) { movie ->
                    onItemClick(movie)
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewListMovie() {
    ListMovie(list = MovieDatasource.getNowPlayingMovie()){}
}