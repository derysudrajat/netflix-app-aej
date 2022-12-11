package id.derysudrajat.netflix.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import id.derysudrajat.netflix.R
import id.derysudrajat.netflix.data.MovieDatasource
import id.derysudrajat.netflix.model.Movie

@Composable
fun DetailMovie(
    movie: Movie,
    onBack: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            val (back, icon, backdrop, poster, divider, play, overview, title, rating, desc) = createRefs()
            // backdrop
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(backdrop) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.ratio("2:3")
                        height = Dimension.fillToConstraints
                    }
                    .drawWithCache {
                        val gradient = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = size.height / 5f,
                            endY = size.height
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient, blendMode = BlendMode.Multiply)
                        }
                    },
                painter = painterResource(id = movie.backdropResourceId), contentDescription = "",
                contentScale = ContentScale.Crop
            )
            // top bar
            Box(
                modifier = Modifier.fillMaxWidth().height(100.dp).drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = size.height,
                        endY = size.height / 2f
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.Multiply)
                    }
                }
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onBack() }
                    .constrainAs(back) {
                        top.linkTo(parent.top, 16.dp)
                        start.linkTo(parent.start, 16.dp)
                    }
            ) {
                Icon(
                    modifier = Modifier.padding(16.dp),
                    imageVector = Icons.Rounded.ArrowBack, contentDescription = "",
                    tint = Color.White
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(icon) {
                        top.linkTo(back.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(back.bottom)
                    },
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.height(32.dp),
                    painter = painterResource(id = R.drawable.ic_netflix), contentDescription = ""
                )
            }
            // divider
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxWidth()
                    .height(5.dp)
                    .constrainAs(divider) {
                        bottom.linkTo(backdrop.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
            // rating
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.constrainAs(rating) {
                    bottom.linkTo(play.top, 16.dp)
                    start.linkTo(play.start)
                    end.linkTo(play.end)
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star, contentDescription = "",
                    tint = Color.White
                )
                Text(text = "${movie.rating}", style = TextStyle(color = Color.White))
            }
            // button play
            Button(
                modifier = Modifier.constrainAs(play) {
                    bottom.linkTo(backdrop.bottom, 64.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                onClick = { /*TODO*/ }
            ) {
                Image(imageVector = Icons.Rounded.PlayArrow, contentDescription = "")
                Text(text = "Play")
            }
            // overview
            Text(
                text = "Overview",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                color = Color.White,
                modifier = Modifier.constrainAs(overview) {
                    top.linkTo(play.bottom, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                }
            )
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .constrainAs(poster) {
                        top.linkTo(overview.bottom, 16.dp)
                        start.linkTo(parent.start, 16.dp)
                        width = Dimension.ratio("2:3")
                        height = Dimension.value(150.dp)
                    },
                painter = painterResource(id = movie.posterResourceId), contentDescription = ""
            )
            Text(
                text = movie.title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                ),
                color = Color.White,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(poster.top)
                    start.linkTo(poster.end, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    width = Dimension.fillToConstraints
                },
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = movie.description,
                color = Color.White,
                modifier = Modifier.constrainAs(desc) {
                    top.linkTo(title.bottom, 8.dp)
                    start.linkTo(poster.end, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    width = Dimension.fillToConstraints
                },
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}

@Preview
@Composable
private fun PreviewDetailMovie() {
    DetailMovie(movie = MovieDatasource.getNowPlayingMovie()[1]){ }
}