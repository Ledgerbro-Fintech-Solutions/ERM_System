package com.example.emiyo.feature.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emiyo.R
import com.example.emiyo.core.design.BodoniModaFontFamily
import com.example.emiyo.core.design.InterFontFamily
import com.example.emiyo.core.design.OnboardingIndicator
import com.example.emiyo.core.design.StyleMatchCard
import com.example.emiyo.core.design.WardrobeCharcoal
import com.example.emiyo.core.design.WardrobeMuted
import com.example.emiyo.core.design.WardrobeSurface
import com.example.emiyo.core.design.WardrobeTheme
import com.example.emiyo.core.design.YourColorsCard

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.ui.input.pointer.pointerInput

/**
 * WARDROBE Onboarding Screen 02
 *
 * "Smarter choices. Better looks."
 * Features three fashion models with dynamic translucent overlays
 * (YOUR COLORS & STYLE MATCH) demonstrating fashion-tech intelligence.
 * Supports horizontal swipe gestures and interactive bottom slider navigation.
 */
@Composable
fun OnboardingScreen2(
    onContinue: () -> Unit = {},
    onNavigateToPrevious: () -> Unit = {},
    onStepClick: ((Int) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(WardrobeSurface)
            .pointerInput(Unit) {
                var totalDrag = 0f
                detectHorizontalDragGestures(
                    onDragStart = { totalDrag = 0f },
                    onDragEnd = {
                        if (totalDrag < -40f) {
                            onContinue()
                        } else if (totalDrag > 40f) {
                            onNavigateToPrevious()
                        }
                    },
                    onHorizontalDrag = { change, dragAmount ->
                        change.consume()
                        totalDrag += dragAmount
                    }
                )
            }
    ) {
        val screenHeight = maxHeight
        val isCompact = screenHeight < 820.dp

        // Layer 1: Clean Editorial Hero Photography (3 models in studio)
        Image(
            painter = painterResource(id = R.drawable.wardrobe_onboarding_02_hero),
            contentDescription = "WARDROBE editorial models styled in neutral and earthy tones",
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        )

        // Layer 2: Top subtle scrim for status bar legibility
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .align(Alignment.TopCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            WardrobeSurface.copy(alpha = 0.70f),
                            WardrobeSurface.copy(alpha = 0.30f),
                            Color.Transparent
                        )
                    )
                )
        )

        // Layer 3: Bottom soft atmospheric ivory fade into punchline
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (isCompact) 320.dp else 380.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            WardrobeSurface.copy(alpha = 0.50f),
                            WardrobeSurface.copy(alpha = 0.88f),
                            WardrobeSurface
                        )
                    )
                )
        )

        // Layer 4: Translucent Fashion-Tech Analysis Overlays
        Box(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            // Overlay 1: YOUR COLORS (Upper Left)
            YourColorsCard(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(
                        start = if (isCompact) 18.dp else 24.dp,
                        top = if (isCompact) 16.dp else 28.dp
                    )
            )

            // Overlay 2: STYLE MATCH (Upper Right, placed staggered below models' shoulders)
            StyleMatchCard(
                score = 92,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(
                        end = if (isCompact) 16.dp else 20.dp,
                        top = if (isCompact) 98.dp else 128.dp
                    )
            )
        }

        // Layer 5: Bottom Editorial Punchline & Onboarding Progress
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(horizontal = 24.dp)
                .padding(bottom = if (isCompact) 24.dp else 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(if (isCompact) 12.dp else 16.dp)
        ) {
            // Major Editorial Headline: "Smarter choices.\nBetter looks."
            val headlineText = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontFamily = BodoniModaFontFamily,
                        fontStyle = FontStyle.Normal,
                        fontSize = if (isCompact) 34.sp else 38.sp,
                        color = WardrobeCharcoal,
                        letterSpacing = (-0.95).sp
                    )
                ) {
                    append("Smarter choices.\n")
                }
                withStyle(
                    style = SpanStyle(
                        fontFamily = BodoniModaFontFamily,
                        fontStyle = FontStyle.Italic,
                        fontSize = if (isCompact) 34.sp else 38.sp,
                        color = WardrobeCharcoal,
                        letterSpacing = (-0.95).sp
                    )
                ) {
                    append("Better looks.")
                }
            }

            Text(
                text = headlineText,
                lineHeight = if (isCompact) 37.sp else 41.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(max = 280.dp)
            )

            // Supporting Statement
            Text(
                text = "Personalized style, powered by you.",
                fontFamily = InterFontFamily,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Normal,
                fontSize = 13.5.sp,
                lineHeight = 22.sp,
                color = WardrobeMuted,
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(max = 280.dp)
            )

            Spacer(modifier = Modifier.height(2.dp))

            // Step 2 Active Indicator (3 segments: inactive | ACTIVE | inactive)
            OnboardingIndicator(
                totalSteps = 3,
                activeStep = 1,
                onStepClick = onStepClick ?: { step ->
                    if (step > 1) onContinue() else if (step < 1) onNavigateToPrevious()
                }
            )
        }
    }
}

@Preview(name = "Standard 390x844", widthDp = 390, heightDp = 844, showBackground = true)
@Composable
private fun OnboardingScreen2StandardPreview() {
    WardrobeTheme {
        OnboardingScreen2()
    }
}

@Preview(name = "Compact 360x800", widthDp = 360, heightDp = 800, showBackground = true)
@Composable
private fun OnboardingScreen2CompactPreview() {
    WardrobeTheme {
        OnboardingScreen2()
    }
}

@Preview(name = "Large 412x915", widthDp = 412, heightDp = 915, showBackground = true)
@Composable
private fun OnboardingScreen2LargePreview() {
    WardrobeTheme {
        OnboardingScreen2()
    }
}
