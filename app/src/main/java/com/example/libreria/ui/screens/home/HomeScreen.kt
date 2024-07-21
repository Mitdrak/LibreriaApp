package com.example.libreria.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.libreria.R
import com.example.libreria.model.BooksResponse
import com.example.libreria.model.Item

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    libreriaUiState: LibreriaUiState, modifier: Modifier = Modifier, contentPadding: PaddingValues
) {
    var searchQuery by remember { mutableStateOf("") }
    val libreriaViewModel: HomeScreemViewModel = viewModel(factory = HomeScreemViewModel.Factory)

    Box(modifier = modifier.padding(top = contentPadding.calculateTopPadding().minus(20.dp))) {
        Column(horizontalAlignment = CenterHorizontally) {
            SearchBar(query = searchQuery,
                onQueryChange = { newQuery -> searchQuery = newQuery },
                onSearch = { libreriaViewModel.getLibros(searchQuery) },
                active = false,
                onActiveChange = {}) {
                Text(text = "Buscar")
            }
            when (libreriaUiState) {
                is LibreriaUiState.Loading -> LoadingScreen(modifier.size(200.dp))
                is LibreriaUiState.Success -> Resultados(
                    libros = libreriaUiState.libros,
                    modifier = modifier,
                    contentPadding = contentPadding
                )

                else -> ErrorScreen({}, modifier)
            }
        }
    }


}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = stringResource(id = R.string.loading),
        modifier = modifier
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.loading_failed))
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = retryAction) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Composable
fun Resultados(libros: BooksResponse, modifier: Modifier, contentPadding: PaddingValues) {
    println(libros.items.size)
    Box(modifier = modifier) {
        LazyColumn(
            modifier = modifier.padding(25.dp), verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            items(items = libros.items, key = { libro -> libro.id!! }) {
                ResultadoCard(it, Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun ResultadoCard(libro: Item, modifier: Modifier = Modifier) {
    Card(modifier = modifier, shape = RoundedCornerShape(8.dp)) {
        Row {
            AsyncImage(
                modifier = modifier.weight(1f),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(libro.volumeInfo?.imageLinks?.thumbnail?.replace("http", "https"))
                    .crossfade(true).build(),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
                error = painterResource(id = R.drawable.ic_broken_image),
            )
            Column(
                modifier = modifier
                    .padding(10.dp)
                    .weight(3f)
            ) {
                Text(
                    text = libro.volumeInfo?.title.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(text = libro.volumeInfo?.authors?.get(0) ?: "")
                Text(
                    text = libro.volumeInfo?.description.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {

}

