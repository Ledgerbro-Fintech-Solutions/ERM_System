package com.example.emiyo.core.design

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.emiyo.R

/**
 * Editorial typography system for WARDROBE.
 *
 * Bodoni Moda: High-fashion editorial display headlines
 * Inter: Clean, legible UI, wordmarks, navigation, and descriptions
 */
val BodoniModaFontFamily = FontFamily(
    Font(R.font.bodoni_moda_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.bodoni_moda_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.bodoni_moda_semibold, FontWeight.SemiBold, FontStyle.Normal)
)

val InterFontFamily = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_regular, FontWeight.Medium),
    Font(R.font.inter_semibold, FontWeight.SemiBold),
    Font(R.font.inter_semibold, FontWeight.Bold)
)

val WardrobeWordmarkStyle = TextStyle(
    fontFamily = InterFontFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.42.em,
    color = WardrobeCharcoal
)

val WardrobeEditorialHeadlineRegularStyle = TextStyle(
    fontFamily = BodoniModaFontFamily,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Normal,
    fontSize = 42.sp,
    lineHeight = 45.sp,
    letterSpacing = 0.em,
    color = WardrobeCharcoal
)

val WardrobeEditorialHeadlineItalicStyle = TextStyle(
    fontFamily = BodoniModaFontFamily,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Italic,
    fontSize = 42.sp,
    lineHeight = 45.sp,
    letterSpacing = 0.em,
    color = WardrobeCharcoal
)

val WardrobeSupportingCopyStyle = TextStyle(
    fontFamily = InterFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 13.5.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.01.em,
    color = WardrobeCharcoalMuted
)

val WardrobePrimaryCtaStyle = TextStyle(
    fontFamily = InterFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 15.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.em,
    color = WardrobeWhite
)

val WardrobeAccountLinkStyle = TextStyle(
    fontFamily = InterFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.16.em,
    color = WardrobeCharcoal.copy(alpha = 0.8f)
)

val WardrobeMaterialTypography = Typography(
    displayLarge = WardrobeEditorialHeadlineRegularStyle,
    bodyLarge = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    labelSmall = WardrobeWordmarkStyle
)
