package com.example.emiyo.feature.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emiyo.R
import com.example.emiyo.core.design.BodoniModaFontFamily
import com.example.emiyo.core.design.ColorSwatchesIcon
import com.example.emiyo.core.design.EditorialBottomLabel
import com.example.emiyo.core.design.FaceAnalysisCard
import com.example.emiyo.core.design.FaceAnalysisEmblem
import com.example.emiyo.core.design.FaceShapeIcon
import com.example.emiyo.core.design.FacialFramingOverlay
import com.example.emiyo.core.design.InterFontFamily
import com.example.emiyo.core.design.SkinToneIcon
import com.example.emiyo.core.design.UndertoneIcon
import com.example.emiyo.core.design.WardrobeCharcoal
import com.example.emiyo.core.design.WardrobeMuted
import com.example.emiyo.core.design.WardrobeSurface
import com.example.emiyo.core.design.WardrobeTheme

/**
 * WARDROBE Screen 10 — "Understand Your Face"
 *
 * Visual Source of Truth: image(3).png / style(3).css
 *
 * Features:
 * - Clean high-end fashion portrait photography of Asian model in linen blazer
 * - Centered top luxury wordmark "WARDROBE"
 * - 4 floating analysis cards (Face Shape, Skin Tone, Undertone, Best Colors)
 * - Delicate white facial alignment frame with horizontal and vertical crosshair axes
 * - Seamless bottom atmospheric gradient transition into solid #FBF9F5
 * - Two-line editorial Bodoni headline: "Understand\nyour face."
 * - Supporting copy in Inter explaining facial feature and undertone analysis
 * - Circular face analysis emblem with outer crosshair ticks in #7D8B72 (Sage)
 * - Editorial bottom label: "FACE ANALYSIS · PERSONALIZED STYLE"
 * - No CTA buttons (pure editorial explanatory screen)
 */
@Composable
fun UnderstandYourFaceScreen(
    onContinue: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onFaceShapeClick: () -> Unit = {},
    onSkinToneClick: () -> Unit = {},
    onUndertoneClick: () -> Unit = {},
    onBestColorsClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    BackHandler(onBack = onBackClick)

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(WardrobeSurface)
    ) {
        val screenHeight = maxHeight
        val isCompact = screenHeight < 820.dp
        val heroHeight = if (isCompact) screenHeight * 0.56f else screenHeight * 0.59f

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Layer 1: Hero Photography & Floating Analysis Badges
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(heroHeight)
            ) {
                // Clean High-End Portrait Photograph
                Image(
                    painter = painterResource(id = R.drawable.wardrobe_understand_face_hero),
                    contentDescription = "Editorial fashion portrait for facial analysis",
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter,
                    modifier = Modifier.fillMaxSize()
                )

                // Atmospheric Top Scrim for Status Bar & Wordmark legibility
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(84.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    WardrobeSurface.copy(alpha = 0.35f),
                                    Color.Transparent
                                )
                            )
                        )
                )

                // Facial Alignment Framing Overlay centered over model's face
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .offset(y = if (isCompact) 130.dp else 144.dp),
                    contentAlignment = Alignment.Center
                ) {
                    FacialFramingOverlay()
                }

                // Left Floating Badge 1: FACE SHAPE (Oval)
                FaceAnalysisCard(
                    label = "FACE SHAPE",
                    value = "Oval",
                    icon = { FaceShapeIcon() },
                    onClick = onFaceShapeClick,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .statusBarsPadding()
                        .offset(
                            x = 16.dp,
                            y = if (isCompact) 18.dp else 24.dp
                        )
                        .width(128.dp)
                )

                // Left Floating Badge 2: SKIN TONE (Warm Ivory)
                FaceAnalysisCard(
                    label = "SKIN TONE",
                    value = "Warm Ivory",
                    icon = { SkinToneIcon() },
                    onClick = onSkinToneClick,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .statusBarsPadding()
                        .offset(
                            x = 18.dp,
                            y = if (isCompact) 72.dp else 78.dp
                        )
                        .width(128.dp)
                )

                // Right Floating Badge 1: UNDERTONE (Warm)
                FaceAnalysisCard(
                    label = "UNDERTONE",
                    value = "Warm",
                    icon = { UndertoneIcon() },
                    onClick = onUndertoneClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .statusBarsPadding()
                        .offset(
                            x = (-12).dp,
                            y = if (isCompact) 74.dp else 82.dp
                        )
                        .width(128.dp)
                )

                // Right Floating Badge 2: BEST COLORS (Earth Tones)
                FaceAnalysisCard(
                    label = "BEST COLORS",
                    value = "Earth\nTones",
                    icon = { ColorSwatchesIcon() },
                    onClick = onBestColorsClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .statusBarsPadding()
                        .offset(
                            x = (-12).dp,
                            y = if (isCompact) 130.dp else 142.dp
                        )
                        .width(128.dp)
                )

                // Atmospheric Gradient Transition into ivory bottom section
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(112.dp)
                        .align(Alignment.BottomCenter)
                        .background(
                            Brush.verticalGradient(
                                0.0f to Color.Transparent,
                                0.50f to WardrobeSurface.copy(alpha = 0.60f),
                                1.0f to WardrobeSurface
                            )
                        )
                )
            }

            // Layer 2: Editorial Typography & Analysis Emblem Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight - heroHeight)
                    .background(WardrobeSurface)
                    .padding(horizontal = 24.dp)
                    .navigationBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Two-Line Editorial Headline: "Understand\nyour face."
                Text(
                    text = "Understand\nyour face.",
                    fontFamily = BodoniModaFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = if (isCompact) 32.sp else 35.sp,
                    lineHeight = if (isCompact) 35.sp else 38.sp,
                    letterSpacing = (-0.35).sp,
                    color = WardrobeCharcoal,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Explanatory Supporting Copy
                Text(
                    text = "We’ll analyze your unique facial features, skin\ntone and undertone to create personalized\nstyle recommendations.",
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.5.sp,
                    lineHeight = 18.5.sp,
                    color = WardrobeMuted,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.widthIn(max = 280.dp)
                )

                Spacer(modifier = Modifier.height(if (isCompact) 18.dp else 26.dp))

                // Circular Face Analysis Emblem
                FaceAnalysisEmblem(onClick = onContinue)

                Spacer(modifier = Modifier.height(10.dp))

                // Bottom Editorial Label
                EditorialBottomLabel(
                    text = "FACE ANALYSIS"
                )
            }
        }
    }
}

@Preview(name = "Standard 390x844", widthDp = 390, heightDp = 844, showBackground = true)
@Composable
private fun UnderstandYourFaceStandardPreview() {
    WardrobeTheme {
        UnderstandYourFaceScreen()
    }
}

@Preview(name = "Compact 360x800", widthDp = 360, heightDp = 800, showBackground = true)
@Composable
private fun UnderstandYourFaceCompactPreview() {
    WardrobeTheme {
        UnderstandYourFaceScreen()
    }
}

@Preview(name = "Large 412x915", widthDp = 412, heightDp = 915, showBackground = true)
@Composable
private fun UnderstandYourFaceLargePreview() {
    WardrobeTheme {
        UnderstandYourFaceScreen()
    }
}
