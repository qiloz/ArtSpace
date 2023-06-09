package com.example.artspace

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = Color(0xFFFFFFFF)
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ArtSpaceApp() {
    val deviceConfig = LocalConfiguration.current

    // TODO: edit ASAP
    val portraitModifier = Modifier
        .fillMaxSize()
        .padding(20.dp)

    val landscapeModifier = Modifier
        .fillMaxSize()

    when (deviceConfig.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            ArtSpaceLandscapeViewImg(modifier = landscapeModifier)
        }
        Configuration.ORIENTATION_PORTRAIT -> {
            ArtSpacePortraitViewImg(modifier = portraitModifier)
        }
        Configuration.ORIENTATION_SQUARE -> {}
        Configuration.ORIENTATION_UNDEFINED -> {}
    }
}

@Composable
fun ArtSpacePortraitViewImg(modifier: Modifier = Modifier) {
    var imageIndex by remember {
        mutableStateOf(3) // TODO: Change to 1
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.width(13.dp))
        ImageContainer(imageIndex, 0.6f)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DescriptionContainer(imageIndex)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val buttonWidth = Modifier.width(80.dp)
                Button(onClick = { /*TODO: Decrement*/ imageIndex-- }) {
                    Text(
                        modifier = buttonWidth, text = "Previous", textAlign = TextAlign.Center
                    )
                }

                Button(onClick = { /*TODO: Increment*/ imageIndex++ }) {
                    Text(
                        modifier = buttonWidth, text = "Next", textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLandscapeViewImg(modifier: Modifier = Modifier) {
    var imageIndex by remember {
        mutableStateOf(3)
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.width(13.dp))
        Row {
            ImageContainer(imageIndex, 0.3f)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DescriptionContainer(imageIndex)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val buttonWidth = Modifier.width(80.dp)
                Button(onClick = { /*TODO: Decrement*/ imageIndex-- }) {
                    Text(
                        modifier = buttonWidth, text = "Previous", textAlign = TextAlign.Center
                    )
                }

                Button(onClick = { /*TODO: Increment*/ imageIndex++ }) {
                    Text(
                        modifier = buttonWidth, text = "Next", textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun ImageContainer(imageIndex: Int = 1, imageSize: Float = 0.8f) {
    val img = when (imageIndex) {
        1 -> R.drawable.picture_1
        2 -> R.drawable.picture_2
        3 -> R.drawable.picture_3
        4 -> R.drawable.picture_4
        5 -> R.drawable.picture_5
        else -> R.drawable.picture_6
    }
    val deviceConfig = LocalConfiguration.current
    val imgPainter = painterResource(id = img)
    val imgWidth = imgPainter.intrinsicSize.width
    val imgHeight = imgPainter.intrinsicSize.height
    Row(
        modifier = Modifier.shadow(1.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (imgHeight.dp / 6 > deviceConfig.screenHeightDp.dp / 4) {
            Image(
                painter = imgPainter,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(20.dp)
                    .requiredSizeIn(
                        minWidth = imgWidth.dp / 6,
                        maxWidth = imgWidth.dp,
                        minHeight = imgHeight.dp,
                        maxHeight = imgHeight.dp / 6
                    )
            )
        } else if (imgHeight.dp / 2 > deviceConfig.screenHeightDp.dp / 2) {
            Image(
                painter = imgPainter,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(20.dp)
                    .requiredSizeIn(
                        minWidth = imgWidth.dp / 3,
                        maxWidth = imgWidth.dp,
                        minHeight = imgHeight.dp,
                        maxHeight = imgHeight.dp / 3
                    )
            )
        } else {
            Image(
                painter = imgPainter,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(20.dp)
                    .requiredSizeIn(
                        minWidth = imgWidth.dp / 2,
                        maxWidth = imgWidth.dp,
                        minHeight = imgHeight.dp,
                        maxHeight = imgHeight.dp / 2
                    )
            )
        }
        // TODO: Realise contentDescription for Image
    }
}

@Composable
fun DescriptionContainer(imageIndex: Int = 1) {
    val titleImg: Int = when (imageIndex) {
        1 -> R.string.title_1
        2 -> R.string.title_2
        3 -> R.string.title_3
        4 -> R.string.title_4
        5 -> R.string.title_5
        else -> R.string.title_6
    }

    val artistImg: Int = when (imageIndex) {
        1 -> R.string.artist_1
        2 -> R.string.artist_2
        3 -> R.string.artist_3
        4 -> R.string.artist_4
        5 -> R.string.artist_5
        else -> R.string.artist_6
    }

    val yearImg: Int = when (imageIndex) {
        1 -> R.string.year_1
        2 -> R.string.year_2
        3 -> R.string.year_3
        4 -> R.string.year_4
        5 -> R.string.year_5
        else -> R.string.year_6
    }

    Column(
        modifier = Modifier
            .shadow(2.dp)
            .padding(15.dp)
    ) {
        Text(stringResource(id = titleImg), fontSize = 25.sp)
        Row {
            Text(stringResource(id = artistImg), fontWeight = FontWeight(800), fontSize = 14.sp)
            Spacer(modifier = Modifier.width(5.dp))
            Text("(${stringResource(id = yearImg)})", fontSize = 14.sp)
        }
    }
}