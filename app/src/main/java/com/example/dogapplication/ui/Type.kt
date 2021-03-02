package com.example.dogapplication.ui

import androidx.compose.material.Typography
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.dogapplication.R

// Set of Material typography styles to start with
val typography = Typography(
        body1 = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
        )
        /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)



private val appFontFamily = FontFamily(
        fonts = listOf(
                Font(
                        resId = R.font.livvic_black,
                        weight = FontWeight.Black,
                        style = FontStyle.Normal
                ),
                Font(
                        resId = R.font.livvic_blackitalic,
                        weight = FontWeight.Black,
                        style = FontStyle.Italic
                ),
                Font(
                        resId = R.font.livvic_bold,
                        weight = FontWeight.Bold,
                        style = FontStyle.Normal
                ),
                Font(
                        resId = R.font.livvic_bolditalic,
                        weight = FontWeight.Bold,
                        style = FontStyle.Italic
                ),
                Font(
                        resId = R.font.livvic_extralight,
                        weight = FontWeight.ExtraLight,
                        style = FontStyle.Normal
                ),
                Font(
                        resId = R.font.livvic_extralightitalic,
                        weight = FontWeight.ExtraLight,
                        style = FontStyle.Italic
                ),
                Font(
                        resId = R.font.livvic_light,
                        weight = FontWeight.Light,
                        style = FontStyle.Normal
                ),
                Font(
                        resId = R.font.livvic_lightitalic,
                        weight = FontWeight.Light,
                        style = FontStyle.Italic
                ),
                Font(
                        resId = R.font.livvic_medium,
                        weight = FontWeight.Medium,
                        style = FontStyle.Normal
                ),
                Font(
                        resId = R.font.livvic_mediumitalic,
                        weight = FontWeight.Medium,
                        style = FontStyle.Italic
                ),
                Font(
                        resId = R.font.livvic_regular,
                        weight = FontWeight.Normal,
                        style = FontStyle.Normal
                ),
                Font(
                        resId = R.font.livvic_semibold,
                        weight = FontWeight.SemiBold,
                        style = FontStyle.Normal
                ),
                Font(
                        resId = R.font.livvic_semibolditalic,
                        weight = FontWeight.SemiBold,
                        style = FontStyle.Italic
                ),
                Font(
                        resId = R.font.livvic_thin,
                        weight = FontWeight.Thin,
                        style = FontStyle.Normal
                ),
                Font(
                        resId = R.font.livvic_thin_italic,
                        weight = FontWeight.Thin,
                        style = FontStyle.Italic
                ),
                Font(
                        resId = R.font.livvicitalic,
                        weight = FontWeight.Normal,
                        style = FontStyle.Italic
                )

)
)

private val defaultTypography = Typography()
val appTypography = Typography(
        h1 = defaultTypography.h1.copy(fontFamily = appFontFamily),
        h2 = defaultTypography.h2.copy(fontFamily = appFontFamily),
        h3 = defaultTypography.h3.copy(fontFamily = appFontFamily),
        h4 = defaultTypography.h4.copy(fontFamily = appFontFamily),
        h5 = defaultTypography.h5.copy(fontFamily = appFontFamily),
        h6 = defaultTypography.h6.copy(fontFamily = appFontFamily),
        subtitle1 = defaultTypography.subtitle1.copy(fontFamily = appFontFamily),
        subtitle2 = defaultTypography.subtitle2.copy(fontFamily = appFontFamily),
        body1 = defaultTypography.body1.copy(fontFamily = appFontFamily),
        body2 = defaultTypography.body2.copy(fontFamily = appFontFamily),
        button = defaultTypography.button.copy(fontFamily = appFontFamily),
        caption = defaultTypography.caption.copy(fontFamily = appFontFamily),
        overline = defaultTypography.overline.copy(fontFamily = appFontFamily)
)
