package xyz.teamgravity.pagingcaching.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import xyz.teamgravity.pagingcaching.presentation.component.CardBeer
import xyz.teamgravity.pagingcaching.presentation.component.ProgressBarCenter
import xyz.teamgravity.pagingcaching.presentation.viewmodel.BeerListViewModel

@Composable
fun BeerListScreen(
    snackbar: SnackbarHostState = remember { SnackbarHostState() },
    viewmodel: BeerListViewModel = hiltViewModel(),
) {
    val beers = viewmodel.beers.collectAsLazyPagingItems()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbar) { data ->
                Snackbar(snackbarData = data)
            }
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            if (beers.loadState.prepend == LoadState.Loading) {
                item {
                    ProgressBarCenter()
                }
            }

            items(
                count = beers.itemCount,
                key = beers.itemKey(
                    key = { beer -> beer.id }
                ),
                contentType = beers.itemContentType()
            ) { index ->
                val beer = beers[index]
                if (beer != null) CardBeer(beer = beer)
            }

            when (val append = beers.loadState.append) {
                LoadState.Loading -> item {
                    ProgressBarCenter()
                }

                is LoadState.Error -> item {
                    LaunchedEffect(key1 = append) {
                        snackbar.showSnackbar(append.error.message ?: "")
                    }
                }

                else -> Unit
            }
        }
    }
}