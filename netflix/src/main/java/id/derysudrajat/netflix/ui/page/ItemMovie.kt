package id.derysudrajat.netflix.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import id.derysudrajat.netflix.data.MovieDatasource
import id.derysudrajat.netflix.model.Movie

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemMovie(movie: Movie, isGrid: Boolean = false, onItemClick: (Movie) -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        onClick = { onItemClick(movie) }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            val (backdrop, title, rating) = createRefs()
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(backdrop) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.ratio(
                            if (isGrid) "3:4" else "16:9"
                        )
                        height = Dimension.fillToConstraints
                    },
                painter = painterResource(id = if (isGrid) movie.posterResourceId else movie.backdropResourceId),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(title) {
                        top.linkTo(backdrop.bottom, 16.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                        width = Dimension.fillToConstraints
                    },
                text = movie.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Black)
                    .constrainAs(rating) {
                        top.linkTo(parent.top, 8.dp)
                        end.linkTo(parent.end, 8.dp)
                    }
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "${movie.rating}",
                    style = TextStyle(color = Color.White)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewItemMovie() {
    ItemMovie(movie = MovieDatasource.getNowPlayingMovie()[0]){}
}