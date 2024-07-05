package com.muradnajafli.rickandmortyapp.presentation.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muradnajafli.rickandmortyapp.domain.model.RickAndMortyModel
import com.muradnajafli.rickandmortyapp.presentation.ui.theme.InterVariable

@Composable
fun CharacterDetails(
    modifier: Modifier = Modifier,
    character: RickAndMortyModel
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val details = listOf(
            "Gender: " to character.gender,
            "Status: " to character.status,
            "Species: " to character.species,
            "Type: " to character.type,
            "Origin: " to character.origin
        )

        details.forEach { (label, text) ->
            TextContentItem(
                label = label,
                text = text
            )
        }
    }
}

@Composable
fun TextContentItem(
    modifier: Modifier = Modifier,
    label: String,
    text: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700,
            fontFamily = FontFamily.InterVariable,
        )
        Text(
            text = text,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.W500,
            fontFamily = FontFamily.InterVariable
        )
    }
}
