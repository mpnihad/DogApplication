package com.example.dogapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dogapplication.Dashboard.Companion.KEY_DOG_DETAIL_OBJECT
import com.example.dogapplication.model.dogitem
import com.example.dogapplication.ui.DogApplicationTheme
import com.example.dogapplication.ui.appTypography
import com.example.dogapplication.ui.grey200
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DogDetails : AppCompatActivity() {
    lateinit var dogDetails: dogitem

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dogDetails = intent.getSerializableExtra(KEY_DOG_DETAIL_OBJECT) as dogitem

        setContent {
            DogApplicationTheme {
                Column {

                    Box() {
                        ContentImage()
                        ContentDetails()

                    }

                }

            }
        }
    }

    @ExperimentalAnimationApi
    @Composable
    private fun ContentDetails() {

        val storyEnabled = remember { mutableStateOf(true) }
        val detailsEnabled = remember { mutableStateOf(false) }
        Column(
            Modifier
                .fillMaxWidth()

        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.32f))
            Column(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(Color.White)

            )
            {


                Row(modifier = Modifier.padding(top = 30.dp)) {
                    Text(
                        dogDetails.Name,
                        style = appTypography.h4.copy(fontWeight = FontWeight.W700),
                        modifier = Modifier.padding(start = 25.dp)
                            .align(alignment = Alignment.CenterVertically)
                    )
                    Box(modifier = Modifier.align(alignment = Alignment.CenterVertically))
                    {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_dog_icon),
                            contentDescription = "Story",
                            modifier = Modifier
                                .height(80.dp).background(shape = CircleShape, color = Color.White)
                                .fillMaxWidth().wrapContentWidth(align = Alignment.End)
                                .padding(15.dp).padding(end = 16.dp)
                        )
                    }
                }

                Row(modifier = Modifier.padding(start = 25.dp, bottom = 30.dp)) {

                    Column {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_dog_story),
                            contentDescription = "Story",
                            modifier = Modifier
                                .border(
                                    BorderStroke(1.dp, color = Color.Black),
                                    shape = CircleShape
                                )
                                .size(65.dp).clickable {
                                    storyEnabled.value = true
                                }.background(shape = CircleShape, color = Color.White)
                                .padding(15.dp)
                        )

                        Text(
                            "Story",
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                        )
                    }
                    Column {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_dog_detail),
                            contentDescription = "Details",
                            modifier = Modifier.padding(start = 16.dp)
                                .border(
                                    BorderStroke(1.dp, color = Color.Black),
                                    shape = CircleShape
                                ).size(65.dp).background(shape = CircleShape, color = Color.White)
                                .clickable {
                                    storyEnabled.value = false
                                }.padding(15.dp)
                        )
                        Text(
                            "Dog Details",
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                        )
                    }
                }

                Column(modifier = Modifier.weight(.7f)) {

                    AnimatedVisibility(!storyEnabled.value) {

                        Row(
                            modifier = Modifier.horizontalScroll(rememberScrollState())
                                .fillMaxHeight()
                        ) {

                            DetailContainer(
                                "Breed",
                                dogDetails.Breed,
                                R.drawable.ic_breed,
                                0xFF53783B
                            )
                            DetailContainer(
                                "Color",
                                dogDetails.Color,
                                R.drawable.ic_color,
                                0xFFDC8875
                            )
                            DetailContainer(
                                "Gender",
                                dogDetails.Sex,
                                R.drawable.ic_gender,
                                0xFF8BB8E1
                            )
                            DetailContainer(
                                "Weight",
                                dogDetails.Weight,
                                R.drawable.ic_weight,
                                0xFFD87659
                            )
                            DetailContainer(
                                "Size",
                                dogDetails.Size,
                                R.drawable.ic_size,
                                0xFF2E4552
                            )

                        }


                    }
                    AnimatedVisibility(
                        storyEnabled.value, modifier = Modifier.verticalScroll(
                            rememberScrollState()
                        ).padding(start = 25.dp)
                    ) {
                        Text(
                            dogDetails.Story,
                            style = MaterialTheme.typography.h6.copy(color = Color.Gray),
                        )
                    }


                }
                Box(modifier = Modifier.weight(0.3f))
                {
                    Box(
                        modifier = Modifier.fillMaxHeight()
                            .wrapContentHeight(align = Alignment.CenterVertically)
                            .height(60.dp)
                            .clickable {
                                Toast.makeText(
                                    this@DogDetails,
                                    "Request Sent tot the owner , Thanks for using this App",
                                    Toast.LENGTH_LONG
                                ).show()


                                CoroutineScope(Dispatchers.IO).launch {
                                    delay(1000)
                                    CoroutineScope(Dispatchers.Main).launch {
                                        finish()
                                    }
                                }


                            }

                            .padding(start = 20.dp, end = 20.dp)
                            .background(grey200, shape = RoundedCornerShape(30.dp))


                    ) {
                        Row {

                            Card(

                                modifier = Modifier.weight(.85f).background(
                                    color = Color.Black,
                                    shape = RoundedCornerShape(30.dp)
                                ).fillMaxHeight(),
                                shape = RoundedCornerShape(30.dp),
                                backgroundColor = Color.Black
                            ) {

                                Text(
                                    "Meet with Pet",
                                    style = appTypography.h5.copy(
                                        fontWeight = FontWeight.W400,
                                        color = grey200
                                    ),
                                    modifier = Modifier.padding(start = 40.dp).wrapContentHeight()
                                        .align(alignment = Alignment.CenterVertically)
                                )
                            }
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_right),
                                contentDescription = "Arrow",
                                modifier = Modifier.padding(end = 20.dp).weight(.15f)
                                    .align(alignment = Alignment.CenterVertically)
                                    .fillMaxWidth().wrapContentWidth(align = Alignment.End),
                                tint = Color.Black
                            )


                        }
                    }
                }
            }


        }

    }

    @Composable
    private fun DetailContainer(
        title: String,
        content: String,
        icon: Int,
        color: Long
    ) {
        Card(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                .clip(shape = RoundedCornerShape(40.dp))
                .width(200.dp).fillMaxHeight(0.9f),
            backgroundColor = Color(color).copy(alpha = 0.8f),
            elevation = 0.dp
        ) {
            Column(modifier = Modifier.background(Color.Transparent).padding(20.dp)) {
                Box(
                    modifier = Modifier.padding(top = 20.dp).size(60.dp)
                )
                {
                    Box(
                        modifier = Modifier.size(60.dp)
                            .background(
                                color = Color.White.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .border(
                                BorderStroke(1.dp, color = Color(color)),
                                shape = RoundedCornerShape(20.dp)
                            )
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(icon),
                        "Icons",
                        modifier = Modifier.align(
                            Alignment.Center
                        ).padding(16.dp),
                        tint = Color(color)
                    )
                }
                Text(
                    title,
                    style = appTypography.h5.copy(
                        fontWeight = FontWeight.W500,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(top = 20.dp)
                )
                Text(
                    content,
                    style = appTypography.subtitle1.copy(
                        fontWeight = FontWeight.W400,
                        color = Color.White
                    ),
                )


            }
        }

    }


    @Composable
    private fun Icons(icIcon: Int, onClick: () -> Unit) {
        IconButton(
            onClick = {
                onClick()
            },
            modifier = Modifier.wrapContentWidth()
                .padding(bottom = 10.dp).clip(shape = CircleShape)
                .background(Color.Black.copy(0.5f))

        )
        {
            Icon(
                imageVector = ImageVector.vectorResource(icIcon), "paw_icon",
                modifier = Modifier
                    .size(75.dp),
            )

        }
    }

    @Composable
    private fun ContentImage() {
        Box()
        {

            CoilImage(
                data = dogDetails.Image,
                contentDescription = dogDetails.Name,
                contentScale = ContentScale.Crop,
                fadeIn = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.35f)

            )
            Box(
                modifier = Modifier.padding(top = 20.dp,start = 20.dp).size(50.dp)
            )
            {
                Box(
                    modifier = Modifier.size(50.dp)
                        .clickable {
                            finish()
                        }
                        .background(
                            color = Color.White.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(15.dp)
                        )

                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_back),
                    "Back",
                    modifier = Modifier
                        .padding(start = 8.dp).align(
                        Alignment.Center
                    ).padding(10.dp)
                )
            }
        }
    }
}