package com.example.dogapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.dogapplication.model.DogData
import com.example.dogapplication.model.dogArrayList
import com.example.dogapplication.model.dogitem
import com.example.dogapplication.ui.DogApplicationTheme
import com.example.dogapplication.ui.appTypography
import com.example.dogapplication.ui.grey200
import com.google.gson.Gson
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Dashboard : AppCompatActivity() {

    var name: String = ""

    var dogList = mutableListOf<dogitem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dogList =
            Gson().fromJson(DogData().getDogList(), dogArrayList::class.java).list.toMutableList()
        setContent {
            DogApplicationTheme {
                val listState = rememberLazyListState()
                val scrollState = rememberScrollState()
                var names by rememberSaveable { mutableStateOf(name) }
                Surface(color = MaterialTheme.colors.background) {

//


                    Column {
                        Column(
                            modifier = Modifier.padding(all = 20.dp)

                        ) {
                            SearchTitle()
                            SearchBar(names)
                            CategoryBox()
                            ListTitle()

                        }

                        DogListRow(listState)

                    }
                }

            }

        }
    }

    @Composable
    private fun DogListRow(listState: LazyListState) {

        LazyRow(

            modifier = Modifier.fillMaxHeight(0.9f)
                .padding(top = 32.dp),
            state = listState

        ) {

            itemsIndexed(items = dogList, itemContent = { index, item ->
                val heightAlpha: Float by animateFloatAsState(if (index - 1 == listState.firstVisibleItemIndex) 1f else 0.7f)
                val widthAlpha: Float by animateFloatAsState(if (index - 1 == listState.firstVisibleItemIndex) 0.6f else 0.4f)

                Box(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .fillParentMaxHeight()
                        .clickable(true, onClick = {
                            var intent= Intent(this@Dashboard,DogDetails::class.java)
                            intent.putExtra(KEY_DOG_DETAIL_OBJECT, item)
                            startActivity(intent)
                        })
                        .wrapContentWidth()
                        .padding(8.dp)


                ) {
                    CoilImage(
                        data = item.Image,
                        contentDescription = item.Name,
                        contentScale = ContentScale.Crop,
                        fadeIn = true,
                        modifier = Modifier.clip(shape = RoundedCornerShape(16.dp))
                            .align(Alignment.Center)
                            .fillParentMaxWidth(widthAlpha)
                            .fillParentMaxHeight(heightAlpha)

                    )
                }
            }
            )

        }
    }

    @Composable
    private fun ListTitle() {
        Text(
            "Adopt Me", style = appTypography.h5.copy(fontWeight = FontWeight.W600),
            modifier = Modifier.padding(top = 40.dp)
        )
    }

    @Composable
    private fun CategoryBox() {
        var itemSelected: MutableState<String> = remember { mutableStateOf("") }


        LazyRow(

            modifier = Modifier.wrapContentWidth()
                .padding(top = 32.dp),
        ) {
            var breedlist = mutableListOf<String>()
            dogList.groupBy {
                it.Breed
            }.forEach {
                breedlist.add(it.key)
            }
            itemsIndexed(items = breedlist, itemContent = { index, item ->
                var categoryBoxColor = remember { Animatable(White) }
                var categoryTextColor = remember { Animatable(Black) }
                Box(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .border(
                            border = BorderStroke(1.dp, Black),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(
                            categoryBoxColor.value
                        )
                        .clickable(true, onClick = {
                            itemSelected.value = item
                            CoroutineScope(Dispatchers.IO).launch {
                                categoryBoxColor.animateTo(Black)
                                categoryTextColor.animateTo(White)
                            }
                        })
                        .wrapContentWidth()
                        .padding(8.dp)


                ) {

                    Text(
                        item,
                        style = MaterialTheme.typography.subtitle1.copy(color = categoryTextColor.value)
                    )
                }
            })

        }
    }


    @Composable
    private fun SearchTitle() {
        Text(
            "Search Friend", style = appTypography.h4.copy(fontWeight = FontWeight.W700),
            modifier = Modifier.padding(top = 60.dp)
        )

    }

    @Composable
    private fun SearchBar(names: String) {

        SearchBox(names)


    }

    @Composable
    private fun SearchBox(name: String) {
        var inputvalue by remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            value = inputvalue,
            onValueChange = { inputvalue = it },
            placeholder = {
                Text("Search...", color = Gray)
            },

            modifier = Modifier
                .padding(top = 30.dp)
                .padding(top = 16.dp, bottom = 16.dp).fillMaxWidth()
                .clip(shape = RoundedCornerShape(25.dp))
                .background(grey200),

            leadingIcon = {
                Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_search), "Search_icon")
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = grey200,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )

        )
        /*  TextField(
              // below line is used to get
              // value of text field,
              value = inputvalue,

              // below line is used to get value in text field
              // on value change in text field.
              onValueChange = { inputvalue=it },

              // below line is used to add placeholder
              // for our text field.
              placeholder = {
                  Text(text = "Enter user name")
              },

              // modifier is use to add padding
              // to our text field.
              modifier = Modifier.padding(all = 16.dp).fillMaxWidth(),

              // keyboard options is used to modify
              // the keyboard for text field.
              keyboardOptions = KeyboardOptions(
                  // below line is use for capitalization
                  // inside our text field.
                  capitalization = KeyboardCapitalization.None,

                  // below line is to enable auto
                  // correct in our keyboard.
                  autoCorrect = true,

                  // below line is used to specify our
                  // type of keyboard such as text, number, phone.
                  keyboardType = KeyboardType.Text,
              ),

              // below line is use to specify
              // styling for our text field value.
              textStyle = TextStyle(color = Black,
                  // below line is used to add font
                  // size for our text field
                  fontSize = 15.dp,

                  // below line is use to change font family.
                  fontFamily = FontFamily.SansSerif),

              // below line is use to give
              // max lines for our text field.
              maxLines = 2,

              // active color is use to change
              // color when text field is focused.
              activeColor = colorResource(id = R.color.purple_200),

              // single line boolean is use to avoid
              // textfield entering in multiple lines.
              singleLine = true,

              // inactive color is use to change
              // color when text field is not focused.
              inactiveColor = androidx.compose.ui.graphics.Color.Gray,

              // below line is use to specify background
              // color for our text field.
              backgroundColor = androidx.compose.ui.graphics.Color.LightGray

              // leading icon is use to add icon
              // at the start of text field.
            *//*  leadingIcon = {
                // In this method we are specifying
                // our leading icon and its color.
                Icon(Icons.Filled.AccountCircle, tint = colorResource(id = R.color.purple_200))
            },
            // trailing icons is use to add
            // icon to the end of tet field.
            trailingIcon = {
                Icon(Icons.Filled.Info, tint = colorResource(id = R.color.purple_200))
            },*//*
        )*/
    }

    companion object {
        public const val KEY_DOG_DETAIL_OBJECT="DogItem"
    }
}