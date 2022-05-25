package com.janayalsalem.movieapp


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.janayalsalem.movieapp.model.Movie
import com.janayalsalem.movieapp.model.getMovies
import com.janayalsalem.movieapp.ui.theme.MovieAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContent {
                MyApp {
                    Content()
                }
            }
        }
    }
}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    MovieAppTheme {
        content()
    }
}


@Composable
fun Content() {
    // top
    Scaffold(topBar = { TopAppBar(backgroundColor = Color.Gray, elevation = 5.dp) { Text(text = "Movies") } })
    {
        // body [Movies List]
        MainContent()
//        Text(text = "Hello")
    }
}


@Composable
fun MainContent() {

    val ListOfMovie = getMovies()

    Scaffold(){
        Column(modifier = Modifier.padding(12.dp)) {
            LazyColumn {
                items(items = ListOfMovie){
                    MovieList(it)
                }
            }
        }
    }
}


@Composable
fun MovieList(movieItem: Movie,onItemClick: (String) -> Unit = {}) {
    var expanded = remember{ mutableStateOf(false) }
    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        //onItemClick(movie.id)
        .clickable { },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Surface(modifier = Modifier
                .padding(12.dp)
                .size(100.dp),
                shape = RectangleShape,
                elevation = 4.dp) {
//                Image(painter = rememberImagePainter(data = movieItem.images[0],
//                    builder = {
//                        crossfade(true)
//                        transformations(CircleCropTransformation())
//                    }),
//                    contentDescription = "Movie Poster")

                // Default image
               Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Movie Image")

            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = movieItem.title,
                    style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movieItem.title}",
                    style = MaterialTheme.typography.caption)
                Text(text = "Released: ${movieItem.year}",
                    style = MaterialTheme.typography.caption)

                AnimatedVisibility(visible = expanded.value) {
                    Column {
                        Text( buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.DarkGray,
                                fontSize = 13.sp)) {
                                append("Plot: ")
                            }
                            withStyle(style = SpanStyle(color = Color.DarkGray,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Light)) {
                                append(movieItem.plot)
                            }

                        }, modifier = Modifier.padding(6.dp))

                        Divider(modifier = Modifier.padding(3.dp))
                        Text(text = "Director: ${movieItem.director}", style = MaterialTheme.typography.caption)
                        Text(text = "Actors: ${movieItem.actors}", style = MaterialTheme.typography.caption)
                        Text(text = "Rating: ${movieItem.rating}", style = MaterialTheme.typography.caption)

                    }
                }



                Icon(imageVector = if (expanded.value ) Icons.Filled.KeyboardArrowDown else
                    Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Down Arrow",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded.value = !expanded.value
                        },
                    tint = Color.DarkGray)
            }


        }



    }

}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        Content()
    }
}