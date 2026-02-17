package com.valer0ld.artistfinder.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.valer0ld.artistfinder.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.tooling.preview.Preview
import com.valer0ld.artistfinder.ui.components.BouncingIconButton
import com.valer0ld.artistfinder.ui.theme.AppTheme

@Composable
fun BottomNavigationBar(
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    onIconClick: (Int)->Unit
){
    Surface(
        shape = RoundedCornerShape(30.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .then(modifier)
            .fillMaxWidth()
            .height(70.dp),
        shadowElevation = 4.dp
    ){
        Row {
            NavBarItems.barItems.forEachIndexed { index, item ->
                val isSelected = index == selectedIndex

                BottomBarItem(
                    item = item,
                    selected = isSelected,
                    onIconClick =  { onIconClick(index) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun BottomBarItem(
    item: BarItem,
    selected: Boolean,
    onIconClick: () -> Unit,
    modifier: Modifier = Modifier
){
    BouncingIconButton(
        icon = item.image,
        label =item.title,
        color = if (selected) {
            MaterialTheme.colorScheme.tertiaryContainer
        } else {
            MaterialTheme.colorScheme.secondaryContainer
        },
        size = 60.dp,
        onIconClick = onIconClick,
        modifier = modifier
    )
}

data class BarItem(
    val title: String,
    val image: Int,
    val route: String
)


object NavBarItems{
    val barItems = listOf(
        BarItem(
            title = "Home",
            image = R.drawable.stadia_controller_24px,
            route = "home"
        ),
        BarItem(
            title = "Profile",
            image = R.drawable.profile_icon,
            route = "profile"
        ),
        BarItem(
            title = "Rating",
            image = R.drawable.social_leaderboard_24px,
            route = "rating"
        )
    )
}

@Preview
@Composable
fun BottomNavBarPreview(){
    AppTheme {
        BottomNavigationBar(1, Modifier, {})
    }
}