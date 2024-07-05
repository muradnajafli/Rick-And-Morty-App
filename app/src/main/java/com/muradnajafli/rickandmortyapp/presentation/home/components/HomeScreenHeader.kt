package com.muradnajafli.rickandmortyapp.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muradnajafli.rickandmortyapp.R
import com.muradnajafli.rickandmortyapp.presentation.home.SearchParams
import com.muradnajafli.rickandmortyapp.presentation.ui.theme.IrishGrover

@Composable
fun HomeScreenHeader(
    modifier: Modifier = Modifier,
    searchParams: SearchParams,
    onSearch: (SearchParams) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
    ) {
        HeaderTexts()

        SearchBar(
            searchParams = searchParams,
            onSearch = onSearch
        )
    }
}

@Composable
fun HeaderTexts(modifier: Modifier = Modifier) {
    Text(
        text = "Rick & Morty",
        fontFamily = FontFamily.IrishGrover,
        fontSize = 44.sp,
        fontWeight = FontWeight.W400,
        color = Color.White
    )
    Text(
        text = "fandom",
        fontFamily = FontFamily.IrishGrover,
        fontSize = 24.sp,
        fontWeight = FontWeight.W400,
        color = Color.White
    )

}

@Composable
fun SearchBar(
    searchParams: SearchParams,
    onSearch: (SearchParams) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = searchParams.queryText ?: "",
        onValueChange = {
            onSearch(searchParams.copy(queryText = it))
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clip(shape = RoundedCornerShape(16.dp)),
        placeholder = {
            Text(
                text = "Search...",
                fontSize = 20.sp,
                fontWeight = FontWeight.W500,
                color = Color(0xFF6B007C).copy(alpha = 0.5f)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                contentDescription = "Search"
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        singleLine = true
    )

}