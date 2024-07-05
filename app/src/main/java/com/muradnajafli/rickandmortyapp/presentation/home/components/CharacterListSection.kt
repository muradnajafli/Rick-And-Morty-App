package com.muradnajafli.rickandmortyapp.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.muradnajafli.rickandmortyapp.R
import com.muradnajafli.rickandmortyapp.domain.model.RickAndMortyModel
import com.muradnajafli.rickandmortyapp.presentation.ui.theme.InterVariable

@Composable
fun CharacterListSection(
    modifier: Modifier = Modifier,
    characterList: List<RickAndMortyModel>,
    onNavigateToDetails: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(
            items = characterList,
            key = { it.id }
        ) { character ->
            CharacterItem(
                character = character,
                onNavigateToDetails = onNavigateToDetails
            )
        }
    }

}

@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    character: RickAndMortyModel,
    onNavigateToDetails: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .background(color = Color(0xFF3A0564))
            .fillMaxWidth()
            .clickable { onNavigateToDetails(character.id) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(160.dp)
                .clip(shape = RoundedCornerShape(20.dp)),
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = "Character Image",
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            )
            when (character.gender) {
                "Male" -> Icon(
                    painter = painterResource(id = R.drawable.ic_gender_male),
                    contentDescription = "Character Icon",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    tint = Color.Unspecified
                )

                "Female" -> Icon(
                    painter = painterResource(id = R.drawable.ic_gender_female),
                    contentDescription = "Character Icon",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    tint = Color.Unspecified
                )

                "Unknown" -> Icon(
                    painter = painterResource(id = R.drawable.ic_gender_unknown),
                    contentDescription = "Character Icon",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                )

                else -> GenderlessIcon(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                )
            }
            if (character.isSaved) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_saved),
                    contentDescription = "Saved Icon",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp),
                    tint = Color.Unspecified
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = character.name,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = FontFamily.InterVariable,
            fontWeight = FontWeight.W700
        )
        Text(
            text = character.species,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 16.sp,
            color = Color.White,
            fontFamily = FontFamily.InterVariable,
            fontWeight = FontWeight.W500
        )
        Spacer(modifier = Modifier.height(32.dp))
    }

}

@Composable
@Preview
fun GenderlessIcon(modifier: Modifier = Modifier) {

    val brush = Brush.linearGradient(
        colors = listOf(
            Color(0xFFDC0000),
            Color(0xFF931212),
            Color(0xFFDC0000)
        )
    )
    Box(
        modifier = modifier
            .size(30.dp)
            .background(
                brush = brush,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(Color.Transparent, shape = CircleShape)
                .border(
                    width = 3.dp,
                    color = Color.White,
                    shape = CircleShape
                )
        )

    }

}