package com.muradnajafli.rickandmortyapp.presentation.home

import com.muradnajafli.rickandmortyapp.presentation.home.components.DropdownMenuList
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.muradnajafli.rickandmortyapp.domain.model.RickAndMortyModel
import com.muradnajafli.rickandmortyapp.presentation.home.components.CharacterListSection
import com.muradnajafli.rickandmortyapp.presentation.home.components.HomeScreenHeader

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    searchParams: SearchParams,
    onSearch: (SearchParams) -> Unit,
    characterList: List<RickAndMortyModel>,
    onNavigateToDetails: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF3A0564))
    ) {
        HomeScreenHeader(
            searchParams = searchParams,
            onSearch = onSearch
        )

        DropdownMenuList(
            onSearch = onSearch,
            searchParams = SearchParams(
                queryText = searchParams.queryText,
                filterParams = searchParams.filterParams,
            )
        )

        CharacterListSection(
            characterList = characterList,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 8.dp)
                .padding(end = 8.dp),
            onNavigateToDetails = onNavigateToDetails
        )

    }

}
