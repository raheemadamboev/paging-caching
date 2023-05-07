package xyz.teamgravity.pagingcaching.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import xyz.teamgravity.pagingcaching.domain.model.BeerModel
import xyz.teamgravity.pagingcaching.domain.usecase.GetBeers
import javax.inject.Inject

@HiltViewModel
class BeerListViewModel @Inject constructor(
    getBeers: GetBeers,
) : ViewModel() {

    val beers: Flow<PagingData<BeerModel>> = getBeers().cachedIn(viewModelScope)
}