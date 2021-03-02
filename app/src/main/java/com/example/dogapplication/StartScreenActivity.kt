package com.example.dogapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Doorbell
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dogapplication.ui.DogApplicationTheme
import dev.chrisbanes.accompanist.coil.CoilImage

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DogApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    SetImage(url = "https://images.unsplash.com/photo-1583512603805-3cc6b41f3edb?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=800&q=80")
                }
            }
        }
    }

    @Composable
    fun SetImage(url: String) {
        Column(
            modifier = Modifier.fillMaxHeight()
                .fillMaxWidth()
                .padding(end = 20.dp, start = 20.dp)
        ) {
            CoilImage(
                data = url,
                contentDescription = url,
                contentScale = ContentScale.Crop,
                fadeIn = true,

                modifier = Modifier.padding(top = 50.dp)
                    .fillMaxHeight(0.6f)
                    .clipToBounds().clip(shape = RoundedCornerShape(45.dp))
            )
            Column(modifier = Modifier.weight(4f)
                .padding(top = 30.dp)
                .fillMaxWidth()) {

                Text(
                    "Hello \nHumans",
                    style = MaterialTheme.typography.h4
                )
                Text(
                    "You've Decided to buy a puppy? \nWe help you find your new Friend",
                    style = MaterialTheme.typography.h6.copy(color = Color.Gray),
                )


            }
            Spacer(modifier = Modifier.weight(1f))

                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_color),
                    contentDescription = "Story",
                    modifier = Modifier
                        .padding(20.dp)
                        .align(alignment = Alignment.End)
                        .size(65.dp).clickable {
                            startActivity(Intent(this@SplashScreen,Dashboard::class.java))

                        }.background(shape = CircleShape, color = Color.Black)
                        .padding(15.dp),
                    tint = Color.White
                )





        }
    }


    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    @Preview()
    @Composable
    fun DefaultPreview() {
        DogApplicationTheme {
            SetImage(url = "")
        }
    }


}

