package id.derysudrajat.netflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import id.derysudrajat.netflix.model.Movie
import id.derysudrajat.netflix.ui.page.DetailMovie
import id.derysudrajat.netflix.ui.theme.MyFirstAppTheme

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.extras?.getParcelable<Movie>(EXTRA_MOVIE)?.let {
            setContent {
                MyFirstAppTheme {
                    // A surface container using the 'background' color from the theme
                    DetailMovie(movie = it) {
                        finish()
                    }
                }
            }
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
}
