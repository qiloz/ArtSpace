package com.example.artspace

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
@Preview(
    showBackground = true,
)
fun ArtSpaceApp() {
    ArtSpaceInstance(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    )
}

@Composable
fun ArtSpaceInstance(
    modifier: Modifier = Modifier
) {
    var numOfPic by remember {
        mutableStateOf(1)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Spacer(
            modifier = Modifier.padding(10.dp)
        )
        // Picture section
        GetPicture(picIndex = numOfPic)

        // Description section
        Spacer(
            modifier = Modifier.padding(10.dp)
        )
        PrintDescription(picIndex = numOfPic)

        Spacer(
            modifier = Modifier.height(5.dp)
        )
        // buttons section
        Row(
            modifier = Modifier.fillMaxWidth(0.8f), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val buttonSize = Modifier.width(100.dp)
            Button(onClick = {
                numOfPic = switchImage(numOfPic, maxItems = 6, inverse = true)
            }, buttonSize) {
                Text(text = "Previous")
            }
            Text(text = numOfPic.toString())
            Button(onClick = {
                numOfPic = switchImage(numOfPic, maxItems = 6, inverse = false)
            }, buttonSize) {
                Text(text = "Next")
            }
        }
    }
}

@Composable
fun GetPicture(
    picIndex: Int,
) {
    val pictureData = when (picIndex) {
        1 -> R.drawable.picture_1
        2 -> R.drawable.picture_2
        3 -> R.drawable.picture_3
        4 -> R.drawable.picture_4
        5 -> R.drawable.picture_5
        else -> R.drawable.picture_6
    }

    Box(
        modifier = Modifier.shadow(1.dp)
    ) {
        val img = painterResource(id = pictureData)
        val sizeImgWidth = img.intrinsicSize.width
        val sizeImgHeight = img.intrinsicSize.height
        Image(
            painter = img,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(10.dp)
                .widthIn(min = sizeImgWidth.dp / 2, max = sizeImgWidth.dp)
                .heightIn(min = sizeImgHeight.dp / 2.4f, max = sizeImgHeight.dp)
        )
    }
}

@Composable
fun PrintDescription(
    picIndex: Int
) {
    val robotoFont = FontFamily(
        Font(R.font.roboto_light, FontWeight.Light)
    )
    val title = when (picIndex) {
        1 -> stringResource(id = R.string.title_1)
        2 -> stringResource(id = R.string.title_2)
        3 -> stringResource(id = R.string.title_3)
        4 -> stringResource(id = R.string.title_4)
        5 -> stringResource(id = R.string.title_5)
        else -> stringResource(id = R.string.title_6)
    }

    val artist = when (picIndex) {
        1 -> stringResource(id = R.string.artist_1)
        2 -> stringResource(id = R.string.artist_2)
        3 -> stringResource(id = R.string.artist_3)
        4 -> stringResource(id = R.string.artist_4)
        5 -> stringResource(id = R.string.artist_5)
        else -> stringResource(id = R.string.artist_6)
    }

    val year = when (picIndex) {
        1 -> stringResource(id = R.string.year_1)
        2 -> stringResource(id = R.string.year_2)
        3 -> stringResource(id = R.string.year_3)
        4 -> stringResource(id = R.string.year_4)
        5 -> stringResource(id = R.string.year_5)
        else -> stringResource(id = R.string.year_6)
    }

    Column(
        modifier = Modifier
            .shadow(1.dp)
            .fillMaxWidth(0.8f)
            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
    ) {
        Text(
            text = title, fontSize = 25.sp, fontFamily = robotoFont, fontWeight = FontWeight.Light
        )
        Row {
            Text(
                modifier = Modifier.padding(end = 5.dp), text = artist, fontWeight = FontWeight(500)
            )
            Text(text = year)
        }
    }
}

fun switchImage(
    value: Int = 1, maxItems: Int = 1, inverse: Boolean = false
): Int {
    var updatedValue: Int = value
    if (!inverse) {
        updatedValue++
        if (updatedValue > maxItems) {
            return 1
        } else {
            return updatedValue
        }
    } else {
        updatedValue--
        if (updatedValue < 1) {
            return maxItems
        } else {
            return updatedValue
        }
    }
}

// TODO: Make open app with random picture with function below
fun getRandomValue(
    values: IntRange
): Int {
    return (values).random()
}