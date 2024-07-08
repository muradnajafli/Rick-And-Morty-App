package com.muradnajafli.rickandmortyapp.presentation.home

import com.muradnajafli.rickandmortyapp.presentation.home.components.DropdownMenuList
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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
    onNavigateToDetails: (Int) -> Unit,
    isLoading: Boolean,
    backgroundColor: Color = Color(0xFF3A0564)
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundColor)
    ) {
        HomeScreenHeader(
            searchParams = searchParams,
            onSearch = onSearch
        )

        DropdownMenuList(
            onSearch = onSearch,
            searchParams = searchParams
        )

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        } else {
            CharacterListSection(
                characterList = characterList,
                onNavigateToDetails = onNavigateToDetails,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 8.dp)
                    .padding(end = 8.dp)
            )
        }

    }

}
