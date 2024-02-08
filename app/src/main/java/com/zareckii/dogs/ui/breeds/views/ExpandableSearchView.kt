package com.zareckii.dogs.ui.breeds.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.zareckii.dogs.R

@Composable
fun ExpandableSearchView(
    searchText: String,
    showSearch: Boolean,
    modifier: Modifier = Modifier,
    onSearchDisplayChanged: (String) -> Unit,
    onSearchDisplayClosed: () -> Unit,
    onExpandedChanged: (Boolean) -> Unit,
    tint: Color = MaterialTheme.colors.onPrimary
) {

    when (showSearch) {
        true -> ExpandedSearchView(
            searchText = searchText,
            onSearchDisplayChanged = onSearchDisplayChanged,
            onSearchDisplayClosed = onSearchDisplayClosed,
            onExpandedChanged = onExpandedChanged,
            modifier = modifier,
            tint = tint
        )

        false -> CollapsedSearchView(
            onExpandedChanged = onExpandedChanged,
            modifier = modifier,
            tint = tint
        )
    }
}

@Composable
fun SearchIcon(iconTint: Color) {
    Icon(
        imageVector = Icons.Default.Search,
        contentDescription = stringResource(R.string.search_icon),
        tint = iconTint
    )
}

@Composable
fun CollapsedSearchView(
    onExpandedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colors.onPrimary,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .widthIn()
            .background(MaterialTheme.colors.primary)
            .padding(vertical = 2.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.body1,
            color = tint,
        )
        IconButton(onClick = { onExpandedChanged(true) }) {
            SearchIcon(iconTint = tint)
        }
    }
}

@Composable
fun ExpandedSearchView(
    searchText: String,
    modifier: Modifier = Modifier,
    onSearchDisplayChanged: (String) -> Unit,
    onSearchDisplayClosed: () -> Unit,
    onExpandedChanged: (Boolean) -> Unit,
    tint: Color = MaterialTheme.colors.onPrimary,
) {
    val focusManager = LocalFocusManager.current

    val textFieldFocusRequester = remember { FocusRequester() }

    SideEffect {
        textFieldFocusRequester.requestFocus()
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            onExpandedChanged(false)
            onSearchDisplayClosed()
        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back_icon),
                tint = tint
            )
        }
        TextField(
            value = searchText,
            onValueChange = { onSearchDisplayChanged(it) },
            trailingIcon = { SearchIcon(iconTint = tint) },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(textFieldFocusRequester),
            label = { Text(text = stringResource(R.string.search), color = tint) },
            colors = TextFieldDefaults.textFieldColors(textColor = tint, cursorColor = tint),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )
    }
}
