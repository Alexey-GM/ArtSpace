package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    val arts by remember { mutableStateOf(Data.arts) }
    var currentArt by remember { mutableIntStateOf(0) }
    Column(
        modifier = modifier
            .padding(horizontal = 32.dp, vertical = 12.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().weight(3f)
        ) {
            ArtSection(
                image = painterResource(arts[currentArt].id)
            )
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            ArtDescriptionSection(
                title = arts[currentArt].title,
                author = arts[currentArt].author,
                year = arts[currentArt].year
            )
            InteractionSection(
                onPreviousClicked = {
                    if (currentArt > 0) {
                        currentArt--
                    }
                },
                onNextClicked = {
                    if (currentArt < arts.lastIndex) {
                        currentArt++
                    }
                }
            )
        }
    }
}

@Composable
fun ArtSection(
    image: Painter,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .shadow(elevation = 4.dp)
            .padding(8.dp),
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier.padding(12.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun ArtDescriptionSection(
    title: String,
    author: String,
    year: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
            .background(Color.LightGray)
    ) {
        Text(
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Light,
            modifier = modifier
                .align(Alignment.Start)
                .padding(start = 12.dp, end = 12.dp, top = 8.dp)
        )
        Text(
            text = stringResource(R.string.year, author, year),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .align(Alignment.Start)
                .padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun InteractionSection(
    onPreviousClicked: () -> Unit,
    onNextClicked: () -> Unit,
) {
    Row {
        Button(
            modifier = Modifier
                .padding(8.dp)
                .weight(1f),
            onClick = onPreviousClicked
        ) {
            Text(
                text = stringResource(R.string.previous)
            )
        }
        Button(
            modifier = Modifier
                .padding(8.dp)
                .weight(1f),
            onClick = onNextClicked
        ) {
            Text(
                text = stringResource(R.string.next)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp(modifier = Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSectionPreview() {
    ArtSpaceTheme {
        ArtSection(
            image = painterResource(Data.arts.first().id),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}