package com.muradnajafli.rickandmortyapp.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muradnajafli.rickandmortyapp.presentation.home.SearchParams
import com.muradnajafli.rickandmortyapp.presentation.ui.theme.InterVariable


@Composable
fun DropdownMenuList(
    modifier: Modifier = Modifier,
    searchParams: SearchParams,
    onSearch: (SearchParams) -> Unit,
) {
    var selectedGender by remember { mutableStateOf(searchParams.filterParams.currentGender) }
    var selectedSpecies by remember { mutableStateOf(searchParams.filterParams.currentSpecies) }
    var selectedStatus by remember { mutableStateOf(searchParams.filterParams.currentStatus) }

    val dropdowns = listOf(
        "Gender Types" to listOf("Male", "Female", "Genderless", "unknown"),
        "Classifications" to listOf("Mythological Creature", "Alien", "Human", "Humanoid", "Animal"),
        "Status" to listOf("Alive", "Dead", "unknown")
    )

    Row(
        modifier = modifier
            .padding(top = 4.dp, bottom = 16.dp)
            .padding(8.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        dropdowns.forEachIndexed { index, dropdownInfo ->
            val (expanded, onExpandedChange) = remember { mutableStateOf(false) }

            val selectedItem = when (index) {
                0 -> selectedGender
                1 -> selectedSpecies
                2 -> selectedStatus
                else -> null
            }

            DropdownMenuBoxWithText(
                expanded = expanded,
                onExpandedChange = onExpandedChange,
                text = selectedItem ?: dropdownInfo.first,
                items = dropdownInfo.second,
                onItemSelected = { item ->
                    when (index) {
                        0 -> selectedGender = if (selectedGender == item) null else item
                        1 -> selectedSpecies = if (selectedSpecies == item) null else item
                        2 -> selectedStatus = if (selectedStatus == item) null else item
                    }
                    onSearch(
                        SearchParams(
                            searchParams.queryText,
                            searchParams.filterParams.copy(
                                currentGender = selectedGender,
                                currentSpecies = selectedSpecies,
                                currentStatus = selectedStatus
                            )
                        )
                    )
                },
                isItemSelected = selectedItem != null,
                onClearItemSelected = {
                    when (index) {
                        0 -> selectedGender = null
                        1 -> selectedSpecies = null
                        2 -> selectedStatus = null
                    }
                    onSearch(
                        SearchParams(
                            searchParams.queryText,
                            searchParams.filterParams.copy(
                                currentGender = selectedGender,
                                currentSpecies = selectedSpecies,
                                currentStatus = selectedStatus
                            )
                        )
                    )
                    onExpandedChange(false)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuBoxWithText(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    text: String,
    items: List<String>,
    isItemSelected: Boolean,
    onItemSelected: (String) -> Unit,
    onClearItemSelected: () -> Unit,
    backgroundColor: Color = Color(0xFFD9D9D9),
    textColor: Color = Color(0xFF6B007C),
    tintColor: Color = Color(0xFF6B007C),
    dividerColor: Color = Color(0xFF6B007C),
    shape: RoundedCornerShape = RoundedCornerShape(50.dp)
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        modifier = modifier
            .background(backgroundColor, shape = shape)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = shape)
                .clickable { if (!isItemSelected) onExpandedChange(!expanded) }
                .menuAnchor(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = textColor.copy(alpha = 0.5f),
                fontFamily = FontFamily.InterVariable,
                fontSize = 16.sp
            )

            val icon = when {
                isItemSelected -> Icons.Default.Close
                expanded -> Icons.Default.KeyboardArrowUp
                else -> Icons.Default.ArrowDropDown
            }

            Icon(
                imageVector = icon,
                contentDescription = if (isItemSelected) "Close" else "Expand",
                tint = tintColor,
                modifier = Modifier.clickable {
                    if (isItemSelected) {
                        onClearItemSelected()
                    } else {
                        onExpandedChange(!expanded)
                    }
                }
            )
        }

        DropdownMenuNoPaddingVertical(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) },
            modifier = Modifier
                .background(Color.White)
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item,
                            fontFamily = FontFamily.InterVariable,
                            color = textColor.copy(alpha = 0.5f),
                        )
                    },
                    onClick = {
                        onItemSelected(item)
                        onExpandedChange(false)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 8.dp)
                )

                HorizontalDivider(
                    color = dividerColor.copy(alpha = 0.5f),
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
            }
        }
    }
}