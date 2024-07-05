package com.muradnajafli.rickandmortyapp.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import coil.compose.AsyncImage
import com.muradnajafli.rickandmortyapp.R
import com.muradnajafli.rickandmortyapp.domain.model.RickAndMortyModel
import com.muradnajafli.rickandmortyapp.presentation.details.components.CharacterDetails
import com.muradnajafli.rickandmortyapp.presentation.details.components.TextContentItem
import com.muradnajafli.rickandmortyapp.presentation.ui.theme.InterVariable
import com.muradnajafli.rickandmortyapp.presentation.ui.theme.IrishGrover

@Composable
fun DetailsScreen(
    character: RickAndMortyModel,
    modifier: Modifier = Modifier,
    onNavigateToBack: () -> Unit,
    onAddOrDeleteFromSaved: (RickAndMortyModel, Boolean) -> Unit,
    isSaved: Boolean
) {
    val bookmarkIcon = if (isSaved) R.drawable.ic_filled_bookmark else R.drawable.ic_bookmark

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF3A0564))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .padding(bottom = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color.Unspecified,
                    modifier = Modifier.clickable { onNavigateToBack() }
                )
                Icon(
                    imageVector = ImageVector.vectorResource(bookmarkIcon),
                    contentDescription = "Bookmark",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .height(35.dp)
                        .width(38.dp)
                        .clickable {
                            onAddOrDeleteFromSaved(character, !isSaved)
                        }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = character.name,
                color = Color.White,
                fontSize = 44.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 24.dp)
                    .padding(end = 24.dp),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.IrishGrover,
                fontWeight = FontWeight.W400,
            )

            Spacer(modifier = Modifier.height(8.dp))

            AsyncImage(
                model = character.image,
                contentDescription = "Character Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(shape = RoundedCornerShape(20.dp))
            )

            Spacer(modifier = Modifier.height(48.dp))

            CharacterDetails(character = character)
        }
    }
}