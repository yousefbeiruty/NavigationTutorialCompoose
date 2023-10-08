package com.example.navigationtutorial.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Colors
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.work.ListenableWorker.Result.retry
import com.example.navigationtutorial.R
import com.example.navigationtutorial.model.MoviesListResponse
import com.example.navigationtutorial.ui.theme.NavigationTutorialTheme
import com.example.navigationtutorial.utils.POSTER_BASE_URL
import com.example.navigationtutorial.viewmodels.MoviesViewModel
import kotlin.math.roundToInt

@Composable
fun HomeDetailsScreen(viewModel: MoviesViewModel= hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        val lis = listOf("Ali", "Mohamed", "Youse",
            "Ali", "Mohamed", "Youse",
            "Ali", "Mohamed", "Youse",
            "Ali", "Mohamed", "Youse")
        val moviePagingItems: LazyPagingItems<MoviesListResponse.Result> = viewModel.moviesList.collectAsLazyPagingItems()
        val topRatedMovies=viewModel.listTopRated.collectAsLazyPagingItems()
        val revenueMovies=viewModel.revenueList.collectAsLazyPagingItems()
        SearchableLazyColumn(movies = moviePagingItems, // Replace with your list of items
            topRaTedList = topRatedMovies,
            revenueList = revenueMovies,
            onSearchTextChanged = { newText ->
                // Handle search text changes here
            }, onItemClick = { selectedItem ->
                // Handle item click here
            })

    }

}

@Preview
@Composable
fun HomeDetailsPreview() {
    NavigationTutorialTheme {
        HomeDetailsScreen()
    }
}

@Preview
@Composable
fun SearchBarPreview() {
//    SearchableLazyColumn(movies =null, // Replace with your list of items
//        topRaTedList = null,
//        revenueList = null,
//        onSearchTextChanged = { newText ->
//            // Handle search text changes here
//        }, onItemClick = { selectedItem ->
//            // Handle item click here
//        })
}

@Composable
fun SearchableLazyColumn(
    movies:  LazyPagingItems<MoviesListResponse.Result>, // Replace with your data type
    topRaTedList:  LazyPagingItems<MoviesListResponse.Result>, // Replace with your data type
    revenueList:  LazyPagingItems<MoviesListResponse.Result>,
    onSearchTextChanged: (String) -> Unit, // Callback for search text changes
    onItemClick: (String) -> Unit,// Callback for item click,
    isError: Boolean = false
) {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        // Search bar
            TextField(modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (isError) Color(0xFFC4C2C2) else Color.LightGray, // Background color with error state
                    shape = RoundedCornerShape(8.dp) // Rounded corners
                ),
                value = searchText,
                onValueChange = {
                    searchText = it
                    onSearchTextChanged(it)
                },
                label = {
                    //  Text(text = "Search")
                },
                singleLine = true, // Display as a single-line input
                textStyle = TextStyle(
                    color = if (isError) Color.Red else Color.Black, // Text color with error state
                    fontSize = 14.sp
                ),
                placeholder = { Text(text = "Search") },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Gray,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                maxLines = 1,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null, // Decorative content, so we set it to null
                        tint = Color.Gray, // Customize the icon color
                        modifier = Modifier.padding(8.dp)
                    )
                }
                // Set the placeholder text
            )
        // LazyColumn
         Text(buildAnnotatedString {
                append("Popular ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)) {
                    append("Card")
                }
                append(" section")
            }, maxLines = 1,

                )
        listofMovies(movies, searchText)
        Text(buildAnnotatedString {
            append("Top Rated ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)) {
                append("Card")
            }
            append(" section")
        }, maxLines = 1,
        )
        listofMovies(topRaTedList, searchText)
        Text(buildAnnotatedString {
            append("Revenue ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)) {
                append("Card")
            }
            append(" section")
        }, maxLines = 1,
        )
        listofMovies(revenueList, searchText)
}
}

@Composable
private fun listofMovies(
    movies: LazyPagingItems<MoviesListResponse.Result>,
    searchText: String
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 1.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        item { Spacer(modifier = Modifier.padding(4.dp)) }
        items(movies.itemSnapshotList.filter {
            it?.originalTitle?.startsWith(searchText, ignoreCase = true) == true
        }.count()) { index ->
            Box(contentAlignment = Alignment.Center) {
                CardDemo(movies[index])
            }
        }

        movies.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = movies.loadState.refresh as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier.fillParentMaxSize(),
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingNextPageItem(modifier = Modifier) }
                }

                loadState.append is LoadState.Error -> {
                    val error = movies.loadState.append as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier,
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }
            }
            item { Spacer(modifier = Modifier.padding(4.dp)) }
        }
    }
}


@Composable
fun PageLoader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.strFetchingDataFromServer),
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        CircularProgressIndicator(Modifier.padding(top = 10.dp))
    }
}

@Composable
fun LoadingNextPageItem(modifier: Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun ErrorMessage(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit
) {
    Row(
        modifier = modifier.padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.weight(1f),
            maxLines = 2
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = stringResource(id = R.string.strRetry))
        }
    }
}


@Composable
fun CardDemo(obj: MoviesListResponse.Result?) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(

        ) {
            NetworkImage(
                contentDescription = "",
                url = POSTER_BASE_URL+obj?.posterPath,
                width = 150,
                height = 150
            )
            TextWithEllipsis(obj?.originalTitle.toString(),15)
        }
    }
}

@Composable
fun TextWithEllipsis(text: String, maxLength: Int) {
    var truncatedText by remember { mutableStateOf(text) }

    if (text.length > maxLength) {
        truncatedText = text.substring(0, maxLength - 2) + ".."
    }
    Text(buildAnnotatedString {
        append("$truncatedText  ")
        withStyle(
            style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))
        ) {
            //     append("Jetpack Compose Playground")
        }
    }, maxLines = 1,
        fontSize = 15.sp,
        modifier = Modifier.padding(5.dp)
    )
}

