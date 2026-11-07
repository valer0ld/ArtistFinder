package com.valer0ld.artistfinder.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.indication
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.valer0ld.artistfinder.domain.model.Artist
import com.valer0ld.artistfinder.domain.model.TempArtist
import com.valer0ld.artistfinder.presentation.theme.AppTheme

@Composable
fun SearchList(artists: List<Artist>, onArtistClick: (String)->Unit, modifier: Modifier = Modifier){
    val listState = rememberLazyListState()

    LazyColumn (
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .shadow(5.dp, RoundedCornerShape(
                bottomStart = 20.dp,
                bottomEnd = 20.dp),
                clip = false
            )
            .clip(
                RoundedCornerShape(
                    topStart = 5.dp,
                    topEnd = 5.dp,
                    bottomStart = 20.dp,
                    bottomEnd = 20.dp,
                )
            )
            .background(MaterialTheme.colorScheme.tertiary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        items(
            items = artists,
            key = {it.name}
        ) { artist ->
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(Color.Transparent)
                    .padding(horizontal = 20.dp)
                    .clickable(onClick = { onArtistClick(artist.name) }),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                AsyncImage(
                    model = artist.image,
                    contentDescription = "default picture",
                    modifier = Modifier
                        .size(30.dp)
                        .clip(RoundedCornerShape(30.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = artist.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onTertiary,
                )
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.2f))
        }
    }
}

@Preview
@Composable
fun SearchListPreview() {
    AppTheme{
        SearchList(TempArtist.list, {})
    }
}