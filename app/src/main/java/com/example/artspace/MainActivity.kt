package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
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
//            .fillMaxHeight(0.8f)
//            .wrapContentSize(Alignment.TopCenter)
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

        // description section
        val robotoFont = FontFamily(
            Font(R.font.roboto_light, FontWeight.Light)
        )

        Spacer(
            modifier = Modifier
                .padding(10.dp)
//            .fillMaxHeight()
        )
        //Picture section
        GetPicture(picIndex = numOfPic)
//
        Spacer(
            modifier = Modifier
                .padding(10.dp)
        )
        Column(
            modifier = Modifier
                .shadow(1.dp)
                .fillMaxWidth(0.8f)
                .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
        ) {
            Text(
                text = "Title",         // TODO: Remove hardcode
                fontSize = 25.sp,
                fontFamily = robotoFont,
                fontWeight = FontWeight.Light
            )
            Row {
                Text(
                    modifier = Modifier
                        .padding(end = 5.dp),
                    text = "Artist",    // TODO: Remove hardcode
                    fontWeight = FontWeight(500)
                )
                Text(text = "(Year)")   // TODO: Remove hardcode

                Text(text = "$numOfPic") // NOTE: DEBUG
            }
        }
        Spacer(
            modifier = Modifier
                .height(5.dp)
        )
        // buttons section
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val buttonSize = Modifier.width(100.dp)
            Button(onClick = {
                numOfPic--
            }, buttonSize) {
                Text(text = "Previous")
            }
            Button(onClick = {
                numOfPic++
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
    val img = painterResource(id = pictureData)
    val sizeImgWidth = img.intrinsicSize.width
    val sizeImgHeight = img.intrinsicSize.height

    Box(
        modifier = Modifier
//            .wrapContentSize(align = Alignment.TopStart, unbounded = true)
            .shadow(1.dp)


    ) {

        Image(
            painter = img,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(10.dp)
//                .wrapContentSize(align = Alignment.TopStart, unbounded = true)
//                .fillMaxSize()
//                .fillMaxWidth(sizeImg.width)
                .widthIn(min = sizeImgWidth.dp / 2, max = sizeImgWidth.dp)
                .heightIn(min = sizeImgHeight.dp / 2.4f, max  = sizeImgHeight.dp)

        )
//        Text(text = sizeImgWidth.toString())
    }

}

fun getRandomValue(
    values: IntRange
): Int {
    return (values).random()
}

