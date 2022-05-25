package com.janayalsalem.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.janayalsalem.movieapp.model.getMovies
import com.janayalsalem.movieapp.ui.theme.ui.theme.MovieAppTheme
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.navigation.NavController
import com.janayalsalem.movieapp.model.Movie


@Preview(showBackground = true)
@ExperimentalAnimationApi
@Composable
fun DefaultPreview2() {
    MovieAppTheme {
//        DetailsScreen("tt0848228")
    }
}

@ExperimentalAnimationApi
@Composable
fun DetailsScreen(navController: NavController,movieId: String?) {
    val newMovieList = getMovies().filter { movie ->
        movie.id == movieId
    }
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Transparent,
            elevation = 0.dp) {
            Row(horizontalArrangement = Arrangement.Start) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription ="Arrow Back",
                    modifier = Modifier.clickable {
                        // back button
                        navController.popBackStack()
                    })
                Spacer(modifier = Modifier.width(100.dp))

                Text(text = "Movies")
            }

        }
    }) {

        Surface(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top) {

                MovieList(movieItem = newMovieList.first())
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Text(text = "Movie Images")
                HorizontalScrollableImageView(newMovieList)
            }
        }
    }




}

@Composable
private fun HorizontalScrollableImageView(newMovieList: List<Movie>) {
//    LazyRow {
//        items(newMovieList[0].images) { image ->
//            Card(modifier = Modifier.padding(12.dp).size(240.dp), elevation = 5.dp) {
//                Image(painter = rememberImagePainter(data = image), contentDescription = "Movie Poster")
//
//            }
//        }
//    }
}