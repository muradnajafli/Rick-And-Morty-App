package com.muradnajafli.rickandmortyapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.muradnajafli.rickandmortyapp.presentation.details.DetailsScreen
import com.muradnajafli.rickandmortyapp.presentation.details.DetailsViewModel
import com.muradnajafli.rickandmortyapp.presentation.home.HomeScreen
import com.muradnajafli.rickandmortyapp.presentation.home.HomeViewModel

@Composable
fun Navigator(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Home,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            composable<Home> {
                val viewModel: HomeViewModel = hiltViewModel()
                val homeUiState by viewModel.uiState.collectAsStateWithLifecycle()
                val searchParameters by viewModel.searchParameters.collectAsStateWithLifecycle()

//                LaunchedEffect(Unit) {
//                    viewModel.fetchCharacters()
//                }

                HomeScreen(
                    onSearch = viewModel::onSearch,
                    searchParams = searchParameters,
                    characterList = homeUiState.characters,
                    onNavigateToDetails = { id ->
                        navController.navigate(Details(id))
                    },
                    isLoading = homeUiState.isLoading
                )
            }

            composable<Details> { backStackEntry ->
                val viewModel: DetailsViewModel = hiltViewModel()
                val character by viewModel.character.collectAsStateWithLifecycle()
                val isSaved by viewModel.isSaved.collectAsStateWithLifecycle()

                val detailsArgs: Details = backStackEntry.toRoute()

                LaunchedEffect(detailsArgs.id) {
                    viewModel.getCharacterById(detailsArgs.id)
                    viewModel.checkIsSaved(detailsArgs.id)
                }

                character?.let {
                    DetailsScreen(
                        character = it,
                        isSaved = isSaved,
                        onNavigateToBack = dropUnlessResumed {
                            navController.popBackStack()
                        },
                        onAddOrDeleteFromSaved = viewModel::addOrRemoveNewsFromSaved
                    )
                }
            }

        }
    }
}